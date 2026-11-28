package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name = "room")
public class Room {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long room_id;

    //Getter and Setter for Room
    public Long getRoom_id() {
        return room_id;
    }
    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }
}
