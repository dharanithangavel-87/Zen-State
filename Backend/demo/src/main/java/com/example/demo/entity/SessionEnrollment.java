package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "session_enrollments")
public class SessionEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "practitioner_id")
    private ZenUser practitioner;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private MeditationSession session;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean completed;


    public SessionEnrollment() {
    }

    public SessionEnrollment(Long id, ZenUser practitioner, MeditationSession session, 
                             LocalDateTime startTime, LocalDateTime endTime, boolean completed) {
        this.id = id;
        this.practitioner = practitioner;
        this.session = session;
        this.startTime = startTime;
        this.endTime = endTime;
        this.completed = completed;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZenUser getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(ZenUser practitioner) {
        this.practitioner = practitioner;
    }

    public MeditationSession getSession() {
        return session;
    }

    public void setSession(MeditationSession session) {
        this.session = session;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}