package com.example.demo.dto;

public class AdminReportDto {

    private long totalUsers;
    private long totalSessions;
    private long totalPlans;
    private long totalEnrollments;

    public AdminReportDto() {
    }

    public AdminReportDto(long totalUsers, long totalSessions, long totalPlans, long totalEnrollments) {
        this.totalUsers = totalUsers;
        this.totalSessions = totalSessions;
        this.totalPlans = totalPlans;
        this.totalEnrollments = totalEnrollments;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalSessions() {
        return totalSessions;
    }

    public void setTotalSessions(long totalSessions) {
        this.totalSessions = totalSessions;
    }

    public long getTotalPlans() {
        return totalPlans;
    }

    public void setTotalPlans(long totalPlans) {
        this.totalPlans = totalPlans;
    }

    public long getTotalEnrollments() {
        return totalEnrollments;
    }

    public void setTotalEnrollments(long totalEnrollments) {
        this.totalEnrollments = totalEnrollments;
    }

}