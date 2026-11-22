package com.example.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable 
public class MaintenanceTaskId implements Serializable {
    
    @Column(name = "maintenance_id")
    private Long maintenance_id;

    @Column(name = "task_number")
    private Integer task_number;

    public MaintenanceTaskId() {}

    public MaintenanceTaskId(Long maintenance_id, Integer task_number) {
        this.maintenance_id = maintenance_id;
        this.task_number = task_number;
    }

    // Getters and Setters
    public Long getMaintenance_id() {
        return maintenance_id;
    }

    public void setMaintenance_id(Long maintenance_id) {
        this.maintenance_id = maintenance_id;
    }

    public Integer getTask_number() {
        return task_number;
    }

    public void setTask_number(Integer task_number) {
        this.task_number = task_number;
    }
    @Override 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaintenanceTaskId that = (MaintenanceTaskId) o;

        if (!maintenance_id.equals(that.maintenance_id)) return false;
        return task_number.equals(that.task_number);
    }
    @Override 
    public int hashCode() {
        return Objects.hash(maintenance_id, task_number);
    }
}
