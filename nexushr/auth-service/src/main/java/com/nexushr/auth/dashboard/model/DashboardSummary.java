package com.nexushr.auth.dashboard.model;

public class DashboardSummary {

    private long totalEmployees;
    private long totalDepartments;
    private long presentToday;
    private long onLeaveToday;
    private long pendingLeaves;
    private long pendingPayrolls;
    private long birthdaysThisMonth;

    public DashboardSummary() {
    }

    public DashboardSummary(
            long totalEmployees,
            long totalDepartments,
            long presentToday,
            long onLeaveToday,
            long pendingLeaves,
            long pendingPayrolls,
            long birthdaysThisMonth) {

        this.totalEmployees = totalEmployees;
        this.totalDepartments = totalDepartments;
        this.presentToday = presentToday;
        this.onLeaveToday = onLeaveToday;
        this.pendingLeaves = pendingLeaves;
        this.pendingPayrolls = pendingPayrolls;
        this.birthdaysThisMonth = birthdaysThisMonth;
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public long getTotalDepartments() {
        return totalDepartments;
    }

    public void setTotalDepartments(long totalDepartments) {
        this.totalDepartments = totalDepartments;
    }

    public long getPresentToday() {
        return presentToday;
    }

    public void setPresentToday(long presentToday) {
        this.presentToday = presentToday;
    }

    public long getOnLeaveToday() {
        return onLeaveToday;
    }

    public void setOnLeaveToday(long onLeaveToday) {
        this.onLeaveToday = onLeaveToday;
    }

    public long getPendingLeaves() {
        return pendingLeaves;
    }

    public void setPendingLeaves(long pendingLeaves) {
        this.pendingLeaves = pendingLeaves;
    }

    public long getPendingPayrolls() {
        return pendingPayrolls;
    }

    public void setPendingPayrolls(long pendingPayrolls) {
        this.pendingPayrolls = pendingPayrolls;
    }

    public long getBirthdaysThisMonth() {
        return birthdaysThisMonth;
    }

    public void setBirthdaysThisMonth(long birthdaysThisMonth) {
        this.birthdaysThisMonth = birthdaysThisMonth;
    }
}