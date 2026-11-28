package com.example.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity 
@Table (name = "trainer_availability")
public class Trainer_Availability {
    
    @EmbeddedId 
    private Trainer_AvailabilityId id;

    @ManyToOne(optional = false)
    @MapsId("trainer")
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @Column (name = "end_at", nullable = false)
    private LocalDateTime end_at;

    // Getter and Setter
    public Trainer_AvailabilityId getId() {
        return id;
    }

    public LocalDateTime getEnd_at() {
        return end_at;
    }

    public Trainer getTrainer() {
        return trainer;
    }
}
