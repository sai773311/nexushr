package com.nexushr.auth.attendance.service;

import com.nexushr.auth.attendance.model.Attendance;
import com.nexushr.auth.attendance.model.AttendanceStatus;
import com.nexushr.auth.attendance.repository.AttendanceRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(
            AttendanceRepository attendanceRepository) {

        this.attendanceRepository = attendanceRepository;
    }

    public Attendance checkIn(
            String email) {

        if (attendanceRepository.findByEmployeeEmailAndAttendanceDate(email, LocalDate.now()).isPresent())
            throw new RuntimeException("Attendance already started for today");

        Attendance attendance = new Attendance();

        attendance.setEmployeeEmail(email);
        attendance.setAttendanceDate(LocalDate.now());
        attendance.setCheckInTime(LocalDateTime.now());
        attendance.setStatus(AttendanceStatus.PRESENT);
        attendance.setWorkMode("OFFICE");

        return attendanceRepository.save(attendance);
    }

    public Attendance checkOut(Long id, String email) {

        Attendance attendance =
                attendanceRepository.findById(id)
                        .orElseThrow();
        if (!attendance.getEmployeeEmail().equals(email)) throw new RuntimeException("Attendance does not belong to user");
        if (attendance.getCheckOutTime() != null) throw new RuntimeException("Attendance already closed");

        LocalDateTime checkout = LocalDateTime.now();
        attendance.setCheckOutTime(checkout);
        long minutes = java.time.Duration.between(attendance.getCheckInTime(), checkout).toMinutes();
        if (attendance.getBreakStartTime() != null && attendance.getBreakEndTime() != null)
            minutes -= java.time.Duration.between(attendance.getBreakStartTime(), attendance.getBreakEndTime()).toMinutes();
        attendance.setWorkingMinutes(Math.max(0, minutes));

        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getMyAttendance(
            String email) {

        return attendanceRepository
                .findByEmployeeEmail(email);
    }

    public List<Attendance> getAllAttendance() {

        return attendanceRepository.findAll();
    }

    public Attendance startBreak(Long id, String email) { return setBreak(id, email, true); }
    public Attendance endBreak(Long id, String email) { return setBreak(id, email, false); }
    private Attendance setBreak(Long id, String email, boolean start) {
        Attendance attendance = attendanceRepository.findById(id).orElseThrow();
        if (!attendance.getEmployeeEmail().equals(email)) throw new RuntimeException("Attendance does not belong to user");
        if (start) attendance.setBreakStartTime(LocalDateTime.now()); else attendance.setBreakEndTime(LocalDateTime.now());
        return attendanceRepository.save(attendance);
    }

    public Attendance requestCorrection(Long id, String email, String reason) {
        Attendance attendance = attendanceRepository.findById(id).orElseThrow();
        if (!attendance.getEmployeeEmail().equals(email)) throw new RuntimeException("Attendance does not belong to user");
        attendance.setCorrectionStatus("PENDING"); attendance.setCorrectionReason(reason);
        return attendanceRepository.save(attendance);
    }

    public Attendance approveCorrection(Long id) {
        Attendance attendance = attendanceRepository.findById(id).orElseThrow();
        attendance.setCorrectionStatus("APPROVED"); return attendanceRepository.save(attendance);
    }
}