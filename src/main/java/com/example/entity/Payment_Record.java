package com.example.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "payment_record")
public class Payment_Record {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "payment_id")
    private Long payment_id;

    @Column (name = "payment_date", nullable = false)
    private LocalDate payment_date;

    @Column (name = "amount", nullable = false)
    private Double amount;
    
    @ManyToOne(optional = false)
    @JoinColumn (name = "member_id", nullable = false)
    private Members member;
}
