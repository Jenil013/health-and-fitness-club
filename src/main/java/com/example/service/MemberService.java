package com.example.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.example.entity.Member;
import com.example.entity.Session;
import com.example.repository.MemberRepository;


public class MemberService {

    // MemberRepository instance
    MemberRepository memberRepository = new MemberRepository();

    
    public void updateFitnessGoals() {
        // Implementation for updating member fitness goals
    }

    public void inputHealthMetrics() {
        // Implementation for inputting health metrics (weight, BMI, etc.)
    }

    public void schedulePrivateSession(Member member) {
        // Implementation for scheduling a private session with a trainer
    }

    public void scheduleGroupClass(Member member) {
        // Implementation for scheduling a group class
        Scanner sc = new Scanner(System.in); 

        // find upcoming group sessions
        List<Session> groups = memberRepository.findUpcomingGroupSessions();

        // list available group sessions
        List<Session> available = new ArrayList<>();
        System.out.println("--- Available Group Classes ---");
        for (Session s : groups) {

            // count enrollment for session
            long enrolled = memberRepository.countEnrollmentForSession(s.getSession_id());
            
            if (enrolled >= s.getCapacity()) continue; // full = not available
            available.add(s);
            System.out.printf(
                "ID: %d | Trainer: %s | Room: %d | %s → %s | Seats: %d/%d%n",
                s.getSession_id(),
                s.getTrainer().getName(),          
                s.getRoom().getRoom_id(),       
                s.getStart_at(),
                s.getEnd_at(),
                enrolled,
                s.getCapacity()
            );
        }
        if (available.isEmpty()) {
            System.out.println("No available group classes right now.");
            return;
        }
        System.out.print("Enter session ID to join (0 = cancel): ");
        long sessionId = sc.nextLong();
        if (sessionId == 0) return;
        Session chosen = available.stream()
            .filter(s -> s.getSession_id().equals(sessionId))
            .findFirst()
            .orElse(null);
        if (chosen == null) {
            System.out.println("Invalid choice or class not available.");
            return;
        }
        boolean alreadyIn = memberRepository.isMemberEnrolled(member.getMember_id(), sessionId);
        if (alreadyIn) {
            System.out.println("You are already registered for this class.");
            return;
        }
        memberRepository.enrollMemberInSession(member.getMember_id(), sessionId);
        System.out.println("Registered for group class " + sessionId);
    }

    public void updatePersonalInfo() {
        // Implementation for updating personal information (address, contact info, etc.)
    }
}
