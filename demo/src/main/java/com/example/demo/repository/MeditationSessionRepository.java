package com.example.demo.repository;

import com.example.demo.entity.MeditationSession;
import com.example.demo.entity.MeditationSession.SessionCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeditationSessionRepository extends JpaRepository<MeditationSession, Long> {

    List<MeditationSession> findByCategory(SessionCategory category);

}