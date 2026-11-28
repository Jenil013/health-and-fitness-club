package com.example.repository;

import java.util.List;
import java.time.LocalDateTime;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.entity.Admin;
import com.example.entity.Member;
import com.example.entity.Room;
import com.example.entity.Trainer;


public class MemberRepository {

    private final SessionFactory sessionFactory =
            new Configuration().configure().buildSessionFactory();

    public void close() {
        sessionFactory.close();
    }

    public List<Member> findAll() {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Member", Member.class).getResultList();
        }
    }

    // List upcoming group classes.
    public List<com.example.entity.Session> findUpcomingGroupSessions() {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM Session s JOIN FETCH s.trainer JOIN FETCH s.room WHERE s.session_type = 'GROUP' AND s.start_at > CURRENT_TIMESTAMP",
                    com.example.entity.Session.class)
                    .getResultList();
        }
    }

    // How many members are in this session. 
    public long countEnrollmentForSession(Long sessionId) {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
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
        try (org.hibernate.Session session = sessionFactory.openSession()) {
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
        try (org.hibernate.Session hibernateSession = sessionFactory.openSession()) {
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


    // Trainers with availability covering [start,end] and no overlapping session. 
    public List<Trainer> findAvailableTrainers(LocalDateTime start, LocalDateTime end) {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            return session.createQuery("""
                SELECT DISTINCT a.trainer FROM Trainer_Availability a 
                    WHERE a.id.start_at <= :start AND a.end_at >= :end 
                    AND a.trainer.trainer_id NOT IN 
                        (SELECT s.trainer.trainer_id FROM Session s 
                        WHERE s.start_at < :end AND :start < s.end_at)""",
                Trainer.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
        }
    }

    // Rooms with no overlapping session in [start,end].
    public List<Room> findAvailableRooms(LocalDateTime start, LocalDateTime end) {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            return session.createQuery("""
            FROM Room r WHERE r.room_id NOT IN (
                    SELECT s.room.room_id FROM Session s 
                        WHERE s.start_at < :end AND :start < s.end_at)""",
                Room.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
        }
    }

    // Creates PRIVATE session (capacity 1) and enrolls member. Returns new session id. 
    public Long bookPrivateSession(Long memberId, Long trainerId, Long roomId,
                                    LocalDateTime start, LocalDateTime end) {
        try (org.hibernate.Session hibernateSession = sessionFactory.openSession()) {
            hibernateSession.beginTransaction();

            Member member = hibernateSession.get(Member.class, memberId);
            Trainer trainer = hibernateSession.get(Trainer.class, trainerId);
            Room room = hibernateSession.get(Room.class, roomId);

            // Demo: first admin in DB (Session requires admin_id)
            Admin admin = hibernateSession.createQuery("FROM Admin", Admin.class)
                    .setMaxResults(1)
                    .uniqueResult();

            if (member == null || trainer == null || room == null || admin == null) {
                hibernateSession.getTransaction().rollback();
                throw new IllegalArgumentException("Member, trainer, room, or admin not found.");
            }

            com.example.entity.Session clubSession = new com.example.entity.Session();
            clubSession.setCapacity(1);
            clubSession.setSession_type("PRIVATE");
            clubSession.setTrainer(trainer);
            clubSession.setRoom(room);
            clubSession.setAdmin(admin);
            clubSession.setStart_at(start);
            clubSession.setEnd_at(end);

            hibernateSession.persist(clubSession);

            member.getSessions().add(clubSession);

            hibernateSession.getTransaction().commit();
            return clubSession.getSession_id();
        }
    }

    public void updateFitnessGoals(Member member, double target_weight, double target_body_fat) {
        try (org.hibernate.Session hibernateSession = sessionFactory.openSession()) {
            hibernateSession.beginTransaction();

            Member existingMember = hibernateSession.get(Member.class, member.getMember_id());
            if (existingMember == null) {
                hibernateSession.getTransaction().rollback();
                throw new IllegalArgumentException("Member not found");
            }

            existingMember.setFitnessGoalTargetWeight(target_weight);
            existingMember.setFitnessGoalTargetBodyFat(target_body_fat);

            hibernateSession.getTransaction().commit();
        }
    }

    public void inputHealthMetrics(Member member, double weight, double height) {
        try (org.hibernate.Session hibernateSession = sessionFactory.openSession()) {
            hibernateSession.beginTransaction();

            Member existingMember = hibernateSession.get(Member.class, member.getMember_id());
            if (existingMember == null) {
                hibernateSession.getTransaction().rollback();
                throw new IllegalArgumentException("Member not found");
            }

            existingMember.setHealthMetricWeight(weight);
            existingMember.setHealthMetricHeight(height);

            hibernateSession.getTransaction().commit();
        }
    }
}