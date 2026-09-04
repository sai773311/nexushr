package com.nexushr.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.nexushr.auth.employee.repository.EmployeeRepository;
import com.nexushr.auth.attendance.repository.AttendanceRepository;
import com.nexushr.auth.leave.repository.LeaveRepository;
import com.nexushr.auth.leave.model.LeaveStatus;
import com.nexushr.auth.performance.repository.PerformanceRepository;
import java.util.Map;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {
    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRepository leaveRepository;
    private final PerformanceRepository performanceRepository;

    public ManagerController(EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository,
            LeaveRepository leaveRepository, PerformanceRepository performanceRepository) {
        this.employeeRepository = employeeRepository; this.attendanceRepository = attendanceRepository;
        this.leaveRepository = leaveRepository; this.performanceRepository = performanceRepository;
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('MANAGER')")
    public Map<String, Long> dashboard() {
        Map<String, Long> result = new LinkedHashMap<>();
        result.put("teamSize", employeeRepository.count()); result.put("attendanceRecords", attendanceRepository.count());
        result.put("pendingLeaves", leaveRepository.countByStatus(LeaveStatus.PENDING));
        result.put("performanceReviews", performanceRepository.count()); return result;
    }
}