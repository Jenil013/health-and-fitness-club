package com.example.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable 
public class Trainer_AvailabilityId implements Serializable {

    @Column(name = "trainer_id")
    private Long trainer;

    @Column(name = "start_at")
    private LocalDateTime start_at;
    
    public Trainer_AvailabilityId() {
    }
    public Trainer_AvailabilityId(Long trainer, LocalDateTime start_at) {
        this.trainer = trainer;
        this.start_at = start_at;
    }
    
    @Override 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trainer_AvailabilityId that = (Trainer_AvailabilityId) o;

        if (!trainer.equals(that.trainer)) return false;
        return start_at.equals(that.start_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainer, start_at);
    }
}
