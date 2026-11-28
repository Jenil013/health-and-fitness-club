package com.example.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "session")
public class Session {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long session_id;

    @Column (name = "capacity", nullable = false)
    private Integer capacity;

    @Column (name = "session_type", nullable = false)
    private String session_type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(optional = false)
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column (name = "start_at", nullable = false)
    private LocalDateTime start_at;

    @Column (name = "end_at", nullable = false)
    private LocalDateTime end_at;

    // Participates table
    @ManyToMany(mappedBy = "sessions")
    private Set<Member> members = new HashSet<>();


    // Getters and Setters
    public Long getSession_id() {
        return session_id;
    }
    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }
    public Integer getCapacity() {
        return capacity;
    }
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    public String getSession_type() {
        return session_type;
    }
    public void setSession_type(String session_type) {
        this.session_type = session_type;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public Trainer getTrainer() {
        return trainer;
    }
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
    public Admin getAdmin() {
        return admin;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public LocalDateTime getStart_at() {
        return start_at;
    }
    public void setStart_at(LocalDateTime start_at) {
        this.start_at = start_at;
    }
    public LocalDateTime getEnd_at() {
        return end_at;
    }
    public void setEnd_at(LocalDateTime end_at) {
        this.end_at = end_at;
    }
    public Set<Member> getMembers() {
        return members;
    }
    public void setMembers(Set<Member> members) {
        this.members = members;
    }
}
