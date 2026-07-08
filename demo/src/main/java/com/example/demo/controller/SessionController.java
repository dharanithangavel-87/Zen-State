package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.MeditationSession;
import com.example.demo.entity.SessionEnrollment;
import com.example.demo.repository.MeditationSessionRepository;
import com.example.demo.service.MeditationSessionService;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final MeditationSessionRepository sessionRepository;
    private final MeditationSessionService sessionService;

    public SessionController(MeditationSessionRepository sessionRepository,
                             MeditationSessionService sessionService) {
        this.sessionRepository = sessionRepository;
        this.sessionService = sessionService;
    }

    @GetMapping
    public ResponseEntity<List<MeditationSession>> getAllSessions() {
        return ResponseEntity.ok(sessionRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getSession(@PathVariable Long id) {
        return ResponseEntity.ok(
                sessionRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Session not found"))
        );
    }


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ZEN_MASTER','WELLNESS_COACH')")
    public ResponseEntity<MeditationSession> createSession(@RequestBody MeditationSession session) {
        return ResponseEntity.ok(sessionService.createSession(session));
    }

    @PostMapping("/{id}/start")
    @PreAuthorize("hasAuthority('PRACTITIONER')")
    public ResponseEntity<SessionEnrollment> startSession(@PathVariable Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(sessionService.startSession(id, userId));
    }

    @PostMapping("/enrollments/{id}/complete")
    @PreAuthorize("hasAuthority('PRACTITIONER')")
    public ResponseEntity<String> completeSession(@PathVariable Long id) {
        sessionService.completeSession(id);
        return ResponseEntity.ok("Session completed successfully");
    }
}