package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "system_alerts")
public class SystemAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String message;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private ZenUser sender;

    // --- Constructors ---

    // Required by JPA/Hibernate
    public SystemAlert() {
    }

    // Parameterized constructor for manual object creation
    public SystemAlert(Long id, String message, LocalDateTime createdAt, ZenUser sender) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
        this.sender = sender;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZenUser getSender() {
        return sender;
    }

    public void setSender(ZenUser sender) {
        this.sender = sender;
    }
}