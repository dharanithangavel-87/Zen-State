package com.example.demo.repository;

import com.example.demo.entity.PlanEnrollment;
import com.example.demo.entity.WellnessPlan;
import com.example.demo.entity.ZenUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanEnrollmentRepository extends JpaRepository<PlanEnrollment, Long> {

    boolean existsByPractitionerAndPlanAndStatus(
            ZenUser practitioner,
            WellnessPlan plan,
            PlanEnrollment.EnrollmentStatus status
    );

}