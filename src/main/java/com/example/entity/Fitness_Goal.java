package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "fitness_goal")
public class Fitness_Goal {
    
    @Id
    @Column(name = "member_id")
    private Long member_id;

    @Column(name = "target_weight")
    private Double target_weight;

    @Column(name = "target_body_fat")
    private Double target_body_fat;

    @OneToOne(optional = false)
    @MapsId 
    @JoinColumn(name = "member_id")
    private Member member;

    public double getTarget_weight() {
        return target_weight;
    }

    public double getTarget_body_fat() {
        return target_body_fat;
    }
    public void setTarget_weight(double target_weight) {
        this.target_weight = target_weight;
    }
    public void setTarget_body_fat(double target_body_fat) {
        this.target_body_fat = target_body_fat;
    }
}
