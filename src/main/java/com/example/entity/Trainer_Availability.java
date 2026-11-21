package com.example.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "trainer_availability")
public class Trainer_Availability {
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @Column (name = "start_at", nullable = false)
    private LocalDateTime start_at;

    @Column (name = "end_at", nullable = false)
    private LocalDateTime end_at;
}
