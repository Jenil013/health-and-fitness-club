package com.example.entity;


@Entity
@Table(name = "equipment")
public class Equipment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipment_id;

    @Column(name = "equipment_name", nullable = false)
    private String equipment_name;

    @Column(name = "status", nullable = false)
    private String status;
}
