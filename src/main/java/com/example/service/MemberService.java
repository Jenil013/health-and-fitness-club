package com.example.service;
import com.example.entity.Member;
import com.example.repository.Database;
import com.example.repository.MemberRepository;

public class MemberService {
     MemberRepository memberRepository = new MemberRepository();

    public void updateFitnessGoals(Member member, double target_weight, double target_body_fat) {
        
        memberRepository.updateFitnessGoals(member, target_weight, target_body_fat);
    }

    public void inputHealthMetrics(Member member, double weight, double height) {
        memberRepository.inputHealthMetrics(member, weight, height);
    }

    public void schedulePrivateSession() {
        // Implementation for scheduling a private session with a trainer
    }

    public void scheduleGroupClass() {
        // Implementation for scheduling a group class
    }

    public void updatePersonalInfo() {
        // Implementation for updating personal information (address, contact info, etc.)
    }
}
