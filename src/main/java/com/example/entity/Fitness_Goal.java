package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fitness_goal")
public class Fitness_Goal {
    
    @Column(name = "target_weight")
    private Double target_weight;

    @Column(name = "target_body_fat")
    private Double target_body_fat;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Members member;
}
