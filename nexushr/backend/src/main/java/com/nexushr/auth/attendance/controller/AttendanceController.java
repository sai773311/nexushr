package com.nexushr.auth.attendance.controller;

import com.nexushr.auth.attendance.service.AttendanceService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(
            AttendanceService attendanceService) {

        this.attendanceService = attendanceService;
    }

    @PostMapping("/check-in")
    public Object checkIn(
            Authentication authentication) {

        return attendanceService.checkIn(
                authentication.getName());
    }

    @PutMapping("/check-out/{id}")
    public Object checkOut(@PathVariable Long id, Authentication authentication) {

        return attendanceService.checkOut(id, authentication.getName());
    }

    @GetMapping("/my-attendance")
    public Object myAttendance(
            Authentication authentication) {

        return attendanceService.getMyAttendance(
                authentication.getName());
    }

    @GetMapping("/all")
    public Object allAttendance() {

        return attendanceService.getAllAttendance();
    }

    @PutMapping("/{id}/break/start")
    public Object startBreak(@PathVariable Long id, Authentication authentication) { return attendanceService.startBreak(id, authentication.getName()); }

    @PutMapping("/{id}/break/end")
    public Object endBreak(@PathVariable Long id, Authentication authentication) { return attendanceService.endBreak(id, authentication.getName()); }

    @PostMapping("/{id}/correction")
    public Object correction(@PathVariable Long id, @RequestParam String reason, Authentication authentication) { return attendanceService.requestCorrection(id, authentication.getName(), reason); }

    @PutMapping("/{id}/correction/approve")
    @org.springframework.security.access.prepost.PreAuthorize("hasAnyRole('MANAGER', 'HR', 'ADMIN')")
    public Object approveCorrection(@PathVariable Long id) { return attendanceService.approveCorrection(id); }
}