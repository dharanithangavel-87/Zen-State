package com.example.demo.service;

import com.example.demo.entity.MeditationSession;
import com.example.demo.entity.SessionEnrollment;
import com.example.demo.entity.ZenUser;
import com.example.demo.repository.MeditationSessionRepository;
import com.example.demo.repository.SessionEnrollmentRepository;
import com.example.demo.repository.ZenUserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class MeditationSessionService {

    private MeditationSessionRepository sessionRepository;
    private SessionEnrollmentRepository enrollmentRepository;
    private ZenUserRepository userRepository;

    // Constructor Injection
    public MeditationSessionService(
            MeditationSessionRepository sessionRepository,
            SessionEnrollmentRepository enrollmentRepository,
            ZenUserRepository userRepository) {

        this.sessionRepository = sessionRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
    }

    // Start a meditation session
    public SessionEnrollment startSession(Long sessionId, Long userId) {

        MeditationSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        ZenUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        SessionEnrollment enrollment = enrollmentRepository
                .findByPractitionerAndSessionAndCompleted(user, session, false)
                .orElse(null);

        if (enrollment == null) {

            enrollment = new SessionEnrollment();
            enrollment.setPractitioner(user);
            enrollment.setSession(session);
            enrollment.setStartTime(LocalDateTime.now());
            enrollment.setCompleted(false);

            enrollment = enrollmentRepository.save(enrollment);
        }

        return enrollment;
    }

    // Complete a meditation session
    public void completeSession(Long enrollmentId) {

        SessionEnrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setCompleted(true);
        enrollment.setEndTime(LocalDateTime.now());

        enrollmentRepository.save(enrollment);

        ZenUser user = enrollment.getPractitioner();

        int minutes = enrollment.getSession().getDurationSeconds() / 60;

        user.setTotalMinutesMeditated(
                user.getTotalMinutesMeditated() + minutes
        );

        LocalDate today = LocalDate.now();

        if (user.getLastMeditationDate() == null) {

            user.setCurrentStreak(1);

        } else if (user.getLastMeditationDate().equals(today.minusDays(1))) {

            user.setCurrentStreak(user.getCurrentStreak() + 1);

        } else if (!user.getLastMeditationDate().equals(today)) {

            user.setCurrentStreak(1);
        }

        user.setLastMeditationDate(today);

        userRepository.save(user);
    }

    // Create a new meditation session
    public MeditationSession createSession(MeditationSession session) {

        return sessionRepository.save(session);
    }
}