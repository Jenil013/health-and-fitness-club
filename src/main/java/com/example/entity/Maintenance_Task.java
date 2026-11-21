package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "maintenance_task")
public class Maintenance_Task {
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "maintenance_id", nullable = false)
    private Maintenance_Record maintenance_record;

    @Column (name = "task_number", nullable = false)
    private Long task_number;

    @Column (name = "repair_task", nullable = false)
    private String repair_task;
}
