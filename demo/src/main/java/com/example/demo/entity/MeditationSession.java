package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "meditation_sessions")
public class MeditationSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private SessionCategory category;

    private Integer durationSeconds;

    private String contentUrl;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;

    public enum SessionCategory {
        STRESS,
        SLEEP,
        FOCUS,
        ANXIETY
    }

    public enum DifficultyLevel {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED
    }

    public MeditationSession() {
    }


    public MeditationSession(Long id, String title, SessionCategory category, Integer durationSeconds, String contentUrl, DifficultyLevel difficultyLevel) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.durationSeconds = durationSeconds;
        this.contentUrl = contentUrl;
        this.difficultyLevel = difficultyLevel;
    }

 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SessionCategory getCategory() {
        return category;
    }

    public void setCategory(SessionCategory category) {
        this.category = category;
    }

    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}