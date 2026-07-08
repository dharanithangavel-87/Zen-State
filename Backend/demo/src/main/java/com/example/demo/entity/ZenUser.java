package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class ZenUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String fullName;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private Integer totalMinutesMeditated = 0;

    private Integer currentStreak = 0;

    private LocalDate lastMeditationDate;

    public ZenUser() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRole getRole() {
        return role;
    }

    public Integer getTotalMinutesMeditated() {
        return totalMinutesMeditated;
    }

    public Integer getCurrentStreak() {
        return currentStreak;
    }

    public LocalDate getLastMeditationDate() {
        return lastMeditationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setTotalMinutesMeditated(Integer totalMinutesMeditated) {
        this.totalMinutesMeditated = totalMinutesMeditated;
    }

    public void setCurrentStreak(Integer currentStreak) {
        this.currentStreak = currentStreak;
    }

    public void setLastMeditationDate(LocalDate lastMeditationDate) {
        this.lastMeditationDate = lastMeditationDate;
    }

    public enum UserRole {
        ZEN_MASTER,
        WELLNESS_COACH,
        PRACTITIONER
    }
}