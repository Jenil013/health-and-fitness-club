package com.example.entity;

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

    // Participates table
    @ManyToMany(mappedBy = "sessions")
    private Set<Members> members = new HashSet<>();
}
