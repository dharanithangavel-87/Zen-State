package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "plan_enrollments")
public class PlanEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "practitioner_id")
    private ZenUser practitioner;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private WellnessPlan plan;

    private LocalDate enrollmentDate;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;


    public enum EnrollmentStatus {
        ACTIVE,
        COMPLETED,
        DROPPED
    }


    public PlanEnrollment() {
    }

   
    public PlanEnrollment(Long id, ZenUser practitioner, WellnessPlan plan, LocalDate enrollmentDate, EnrollmentStatus status) {
        this.id = id;
        this.practitioner = practitioner;
        this.plan = plan;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
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

    public WellnessPlan getPlan() {
        return plan;
    }

    public void setPlan(WellnessPlan plan) {
        this.plan = plan;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
}