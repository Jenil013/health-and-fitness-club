package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "health_metric")
public class Health_Metric {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "health_metric_id")
    private int health_metric_id;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "body_fat")
    private Double body_fat;

    @Column(name = "height")
    private Double height;

    @Column(name = "heart_rate")
    private Integer heart_rate;

    @ManyToOne(optional = false)
    @JoinColumn (name = "member_id", nullable = false)
    private Member member;
}