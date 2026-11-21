package com.example.entity;

import java.lang.annotation.Inherited;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admin_id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
