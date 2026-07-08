package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdminReportDto;
import com.example.demo.repository.MeditationSessionRepository;
import com.example.demo.repository.PlanEnrollmentRepository;
import com.example.demo.repository.WellnessPlanRepository;
import com.example.demo.repository.ZenUserRepository;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ZenUserRepository userRepository;
    private final MeditationSessionRepository sessionRepository;
    private final WellnessPlanRepository planRepository;
    private final PlanEnrollmentRepository enrollmentRepository;

    public ReportController(ZenUserRepository userRepository,
                            MeditationSessionRepository sessionRepository,
                            WellnessPlanRepository planRepository,
                            PlanEnrollmentRepository enrollmentRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.planRepository = planRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ZEN_MASTER')")
    public ResponseEntity<AdminReportDto> getAdminReport() {

        AdminReportDto report = new AdminReportDto();
    
        report.setTotalUsers(userRepository.count());
        report.setTotalSessions(sessionRepository.count());
        report.setTotalPlans(planRepository.count());
        report.setTotalEnrollments(enrollmentRepository.count());

       return ResponseEntity.ok(report);
}
}