package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wellness_plans")
public class WellnessPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planName;

    private String targetGoal;

    private Integer capacity;

    private Integer currentEnrollments = 0;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private ZenUser creator;

    public WellnessPlan() {
    }

    public WellnessPlan(Long id, String planName, String targetGoal,
                        Integer capacity, Integer currentEnrollments,
                        ZenUser creator) {
        this.id = id;
        this.planName = planName;
        this.targetGoal = targetGoal;
        this.capacity = capacity;
        this.currentEnrollments = currentEnrollments;
        this.creator = creator;
    }


    public Long getId() {
        return id;
    }

    public String getPlanName() {
        return planName;
    }

    public String getTargetGoal() {
        return targetGoal;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getCurrentEnrollments() {
        return currentEnrollments;
    }

    public ZenUser getCreator() {
        return creator;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public void setTargetGoal(String targetGoal) {
        this.targetGoal = targetGoal;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setCurrentEnrollments(Integer currentEnrollments) {
        this.currentEnrollments = currentEnrollments;
    }

    public void setCreator(ZenUser creator) {
        this.creator = creator;
    }

}