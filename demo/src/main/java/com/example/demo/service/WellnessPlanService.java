package com.example.demo.service;

import com.example.demo.entity.PlanEnrollment;
import com.example.demo.entity.WellnessPlan;
import com.example.demo.entity.ZenUser;
import com.example.demo.repository.PlanEnrollmentRepository;
import com.example.demo.repository.WellnessPlanRepository;
import com.example.demo.repository.ZenUserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class WellnessPlanService {

    private WellnessPlanRepository planRepository;
    private PlanEnrollmentRepository enrollmentRepository;
    private ZenUserRepository userRepository;

    // Constructor Injection
    public WellnessPlanService(
            WellnessPlanRepository planRepository,
            PlanEnrollmentRepository enrollmentRepository,
            ZenUserRepository userRepository) {

        this.planRepository = planRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public PlanEnrollment enrollPractitioner(Long userId, Long planId) {

        ZenUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        WellnessPlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        // Check whether the plan has reached its maximum capacity
        if (plan.getCurrentEnrollments() >= plan.getCapacity()) {
            throw new RuntimeException("Plan capacity reached");
        }

        // Check whether the user is already enrolled
        boolean alreadyEnrolled = enrollmentRepository
                .existsByPractitionerAndPlanAndStatus(
                        user,
                        plan,
                        PlanEnrollment.EnrollmentStatus.ACTIVE
                );

        if (alreadyEnrolled) {
            throw new RuntimeException("Already enrolled in this plan");
        }

        // Increase the current enrollment count
        plan.setCurrentEnrollments(plan.getCurrentEnrollments() + 1);
        planRepository.save(plan);

        // Create a new enrollment
        PlanEnrollment enrollment = new PlanEnrollment();
        enrollment.setPractitioner(user);
        enrollment.setPlan(plan);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setStatus(PlanEnrollment.EnrollmentStatus.ACTIVE);

        return enrollmentRepository.save(enrollment);
    }

    // Create a new wellness plan
    public WellnessPlan createPlan(WellnessPlan plan, Long creatorId) {

        ZenUser creator = userRepository.findById(creatorId)
                .orElseThrow(() -> new RuntimeException("Creator not found"));

        plan.setCreator(creator);
        plan.setCurrentEnrollments(0);

        return planRepository.save(plan);
    }
}