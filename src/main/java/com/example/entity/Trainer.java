package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name = "trainer")
public class Trainer {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainer_id;

    @Column (name = "name", nullable = false)
    private String name;

    //Getter and Setter for Trainer
    public String getName() {
        return name;
    }
}