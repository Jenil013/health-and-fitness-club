package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity 
@Table (name = "maintenance_task")
public class Maintenance_Task {
    

    @EmbeddedId
    private MaintenanceTaskId id;

    @ManyToOne(optional = false)
    @MapsId("maintenance_id")
    @JoinColumn(name = "maintenance_id", nullable = false)
    private Maintenance_Record maintenance_record;

    @Column (name = "repair_task", nullable = false)
    private String repair_task;
}
