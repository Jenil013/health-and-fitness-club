package com.example.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate date_of_birth;

    // Participates table
    @ManyToMany
    @JoinTable(
        name = "participates",
        joinColumns = @JoinColumn(name = "member_id"),
        inverseJoinColumns = @JoinColumn(name = "session_id")
    )

    private Fitness_Goal fitnessGoal;
    private Health_Metric healthMetric;

    private Set<Session> sessions = new HashSet<>();

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    public Fitness_Goal getFitnessGoal() {
        return fitnessGoal;
    }
    public void setFitnessGoalTargetBodyFat(double target_body_fat) {
        this.fitnessGoal.setTarget_body_fat(target_body_fat);
    }
    public void setFitnessGoalTargetWeight(double target_weight) {
        this.fitnessGoal.setTarget_weight(target_weight);
    }

    public void setHealthMetricWeight(double weight) {
        this.healthMetric.setWeight(weight);
    }
    public void setHealthMetricHeight(double height) {
        this.healthMetric.setHeight(height);
    }
    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }
}
