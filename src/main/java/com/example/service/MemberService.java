package com.example.service;
import com.example.entity.Member;
import com.example.repository.Database;
import com.example.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.example.entity.Session;
import com.example.entity.Trainer;
import com.example.entity.Room;


public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void updateFitnessGoals(Member member, double target_weight, double target_body_fat) {
        
        memberRepository.updateFitnessGoals(member, target_weight, target_body_fat);
    }

    public void inputHealthMetrics(Member member, double weight, double height) {
        memberRepository.inputHealthMetrics(member, weight, height);
    }

    public void schedulePrivateSession(Member member) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("--- Schedule Private Session ---");
        System.out.print("Start (yyyy-MM-dd HH:mm): ");
        String startStr = sc.nextLine().trim();
        System.out.print("End   (yyyy-MM-dd HH:mm): ");
        String endStr = sc.nextLine().trim();

        LocalDateTime start;
        LocalDateTime end;
        try {
            start = LocalDateTime.parse(startStr, fmt);
            end = LocalDateTime.parse(endStr, fmt);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format.");
            return;
        }

        if (!end.isAfter(start)) {
            System.out.println("End must be after start.");
            return;
        }

        List<Trainer> trainers = memberRepository.findAvailableTrainers(start, end);
        if (trainers.isEmpty()) {
            System.out.println("No trainers available for that time.");
            return;
        }
        System.out.println("--- Available Trainers ---");
        for (Trainer t : trainers) {
            System.out.printf("ID: %d | Name: %s%n", t.getTrainer_id(), t.getName());
        }

        List<Room> rooms = memberRepository.findAvailableRooms(start, end);
        if (rooms.isEmpty()) {
            System.out.println("No rooms available for that time.");
            return;
        }
        System.out.println("--- Available Rooms ---");
        for (Room r : rooms) {
            System.out.printf("ID: %d%n", r.getRoom_id());
        }

        System.out.print("Pick Trainer ID (0 = cancel): ");
        long trainerId = sc.nextLong();
        if (trainerId == 0) return;
        boolean validTrainer = trainers.stream()
                .anyMatch(t -> t.getTrainer_id().equals(trainerId));
        if (!validTrainer) {
            System.out.println("Invalid trainer (must pick from the list).");
            return;
        }

        System.out.print("Pick Room ID (0 = cancel): ");
        long roomId = sc.nextLong();
        if (roomId == 0) return;
        boolean validRoom = rooms.stream()
                .anyMatch(r -> r.getRoom_id().equals(roomId));
        if (!validRoom) {
            System.out.println("Invalid room (must pick from the list).");
            return;
        }

        try {
            Long sessionId = memberRepository.bookPrivateSession(
                    member.getMember_id(), trainerId, roomId, start, end);
            System.out.println("Private session booked. Session ID = " + sessionId);
        } catch (IllegalArgumentException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
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
