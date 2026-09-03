package com.nexushr.auth.controller;

import com.nexushr.auth.service.AuthService;
import com.nexushr.auth.attendance.repository.AttendanceRepository;
import com.nexushr.auth.leave.repository.LeaveRepository;
import com.nexushr.auth.payroll.repository.PayrollRepository;
import com.nexushr.auth.document.repository.EmployeeDocumentRepository;
import com.nexushr.auth.training.repository.TrainingProgramRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ess")
@PreAuthorize("hasRole('EMPLOYEE')")
public class EssController {
    private final AuthService authService;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRepository leaveRepository;
    private final PayrollRepository payrollRepository;
    private final EmployeeDocumentRepository documentRepository;
    private final TrainingProgramRepository trainingRepository;
    public EssController(AuthService authService, AttendanceRepository attendanceRepository, LeaveRepository leaveRepository, PayrollRepository payrollRepository, EmployeeDocumentRepository documentRepository, TrainingProgramRepository trainingRepository) { this.authService = authService; this.attendanceRepository = attendanceRepository; this.leaveRepository = leaveRepository; this.payrollRepository = payrollRepository; this.documentRepository = documentRepository; this.trainingRepository = trainingRepository; }
    @GetMapping public Map<String, Object> selfService(Authentication authentication) {
        var user = authService.getUser(authentication.getName()); Map<String, Object> result = new LinkedHashMap<>();
        result.put("profile", Map.of("id", user.getId(), "fullName", user.getFullName(), "email", user.getEmail(), "role", user.getRole().name()));
        result.put("attendance", attendanceRepository.findByEmployeeEmail(authentication.getName())); result.put("leave", leaveRepository.findByEmployeeEmail(authentication.getName()));
        result.put("payroll", payrollRepository.findByEmployeeEmail(authentication.getName())); result.put("documents", documentRepository.findByEmployeeId(user.getId()));
        result.put("training", trainingRepository.findByAssignedEmployeeEmail(authentication.getName())); return result;
    }
}