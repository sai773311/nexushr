package com.nexushr.auth.attendance.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeEmail;

    private LocalDate attendanceDate;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;
    private LocalDateTime breakStartTime;
    private LocalDateTime breakEndTime;
    private String workMode;
    private String correctionStatus;
    private String correctionReason;
    private Long workingMinutes;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    public Attendance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public LocalDateTime getBreakStartTime() { return breakStartTime; }
    public void setBreakStartTime(LocalDateTime value) { breakStartTime = value; }
    public LocalDateTime getBreakEndTime() { return breakEndTime; }
    public void setBreakEndTime(LocalDateTime value) { breakEndTime = value; }
    public String getWorkMode() { return workMode; }
    public void setWorkMode(String value) { workMode = value; }
    public String getCorrectionStatus() { return correctionStatus; }
    public void setCorrectionStatus(String value) { correctionStatus = value; }
    public String getCorrectionReason() { return correctionReason; }
    public void setCorrectionReason(String value) { correctionReason = value; }
    public Long getWorkingMinutes() { return workingMinutes; }
    public void setWorkingMinutes(Long value) { workingMinutes = value; }
}