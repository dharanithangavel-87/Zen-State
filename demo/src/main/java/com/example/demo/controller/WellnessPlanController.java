package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PlanEnrollment;
import com.example.demo.entity.WellnessPlan;
import com.example.demo.repository.WellnessPlanRepository;
import com.example.demo.service.WellnessPlanService;

@RestController
@RequestMapping("/api/plans")
public class WellnessPlanController {

    private final WellnessPlanRepository planRepository;
    private final WellnessPlanService planService;

    public WellnessPlanController(WellnessPlanRepository planRepository,
                                  WellnessPlanService planService) {
        this.planRepository = planRepository;
        this.planService = planService;
    }


    @GetMapping
    public ResponseEntity<List<WellnessPlan>> getAllPlans() {
        return ResponseEntity.ok(planRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WellnessPlan> getPlan(@PathVariable Long id) {
        return ResponseEntity.ok(
                planRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Plan not found"))
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ZEN_MASTER','WELLNESS_COACH')")
    public ResponseEntity<WellnessPlan> createPlan(
            @RequestBody WellnessPlan plan,
            @RequestParam Long creatorId
    ) {
        return ResponseEntity.ok(
                planService.createPlan(plan, creatorId)
        );
    }

    @PostMapping("/{id}/enroll")
    @PreAuthorize("hasAuthority('PRACTITIONER')")
    public ResponseEntity<PlanEnrollment> enroll(@PathVariable Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(
                planService.enrollPractitioner(userId, id)
        );
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ZEN_MASTER','WELLNESS_COACH')")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        planRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}