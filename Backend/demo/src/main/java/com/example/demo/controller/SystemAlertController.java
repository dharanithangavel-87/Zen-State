package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SystemAlert;
import com.example.demo.entity.ZenUser;
import com.example.demo.repository.SystemAlertRepository;
import com.example.demo.repository.ZenUserRepository;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*")
public class SystemAlertController {

    private final SystemAlertRepository alertRepository;
    private final ZenUserRepository userRepository;

    public SystemAlertController(SystemAlertRepository alertRepository,
                                 ZenUserRepository userRepository) {
        this.alertRepository = alertRepository;
        this.userRepository = userRepository;
    }


    @GetMapping
    public ResponseEntity<List<SystemAlert>> getAlerts() {
        return ResponseEntity.ok(
                alertRepository.findTop5ByOrderByCreatedAtDesc()
        );
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ZEN_MASTER')")
    public ResponseEntity<SystemAlert> createAlert(
            @RequestParam Long senderId,
            @RequestBody String message
    ) {

        ZenUser sender = userRepository.findById(senderId)
                .orElseThrow(() ->
                        new RuntimeException("Sender not found"));

        SystemAlert alert = new SystemAlert();

        alert.setMessage(message);
        alert.setCreatedAt(LocalDateTime.now());
        alert.setSender(sender);

        return ResponseEntity.ok(
                alertRepository.save(alert)
        );
    }
}