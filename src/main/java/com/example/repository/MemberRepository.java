package com.example.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.entity.Admin;
import com.example.entity.Member;
import com.example.entity.Room;
import com.example.entity.Trainer;

public class MemberRepository {

    private SessionFactory buildFactory() {
        return new Configuration()
                .addAnnotatedClass(Member.class)
                .addAnnotatedClass(com.example.entity.Session.class)
                .addAnnotatedClass(Trainer.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Admin.class)
                .buildSessionFactory();
    }

    // List upcoming group classes.
    public List<com.example.entity.Session> findUpcomingGroupSessions() {
        try (org.hibernate.Session session = buildFactory().openSession()) {
            return session.createQuery(
                    "FROM Session s JOIN FETCH s.trainer JOIN FETCH s.room WHERE s.session_type = 'GROUP' AND s.start_at > CURRENT_TIMESTAMP",
                    com.example.entity.Session.class)
                    .getResultList();
        }
    }

    // How many members are in this session. 
    public long countEnrollmentForSession(Long sessionId) {
        try (org.hibernate.Session session = buildFactory().openSession()) {
            Long count = session.createQuery(
                    "SELECT COUNT(m) FROM Member m JOIN m.sessions s WHERE s.session_id = :id",
                    Long.class)
                    .setParameter("id", sessionId)
                    .getSingleResult();
            return count == null ? 0L : count;
        }
    }

    // Check if member is enrolled in session
    public boolean isMemberEnrolled(Long memberId, Long sessionId) {
        try (org.hibernate.Session session = buildFactory().openSession()) {
            return session.createQuery(
                    "SELECT COUNT(m) FROM Member m JOIN m.sessions s WHERE m.member_id = :memberId AND s.session_id = :sessionId",
                    Long.class)
                    .setParameter("memberId", memberId)
                    .setParameter("sessionId", sessionId)
                    .getSingleResult() > 0;
        }
    }

    // Enroll member in session
    public void enrollMemberInSession(Long memberId, Long sessionId) {
        try (org.hibernate.Session hibernateSession = buildFactory().openSession()) {
            hibernateSession.beginTransaction();

            Member member = hibernateSession.get(Member.class, memberId);
            com.example.entity.Session clubSession =
                    hibernateSession.get(com.example.entity.Session.class, sessionId);

            if (member == null || clubSession == null) {
                hibernateSession.getTransaction().rollback();
                throw new IllegalArgumentException("Member or session not found.");
            }

            member.getSessions().add(clubSession);
            hibernateSession.getTransaction().commit();
        }
    }


}