package com.nexushr.auth.attendance.repository;

import com.nexushr.auth.attendance.model.Attendance;
import com.nexushr.auth.attendance.model.AttendanceStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    List<Attendance> findByEmployeeEmail(
            String employeeEmail);
        java.util.Optional<Attendance> findByEmployeeEmailAndAttendanceDate(String employeeEmail, java.time.LocalDate date);
        List<Attendance> findByEmployeeEmailAndCorrectionStatus(String employeeEmail, String status);
        long countByAttendanceDateAndStatus(java.time.LocalDate date, AttendanceStatus status);
}