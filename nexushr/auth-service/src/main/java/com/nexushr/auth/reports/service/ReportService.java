package com.nexushr.auth.reports.service;

import com.nexushr.auth.department.repository.DepartmentRepository;
import com.nexushr.auth.attendance.repository.AttendanceRepository;
import com.nexushr.auth.leave.repository.LeaveRepository;
import com.nexushr.auth.payroll.repository.PayrollRepository;
import com.nexushr.auth.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRepository leaveRepository;
    private final PayrollRepository payrollRepository;

    public ReportService(
            UserRepository userRepository,
            DepartmentRepository departmentRepository,
            AttendanceRepository attendanceRepository,
            LeaveRepository leaveRepository,
            PayrollRepository payrollRepository) {

        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.attendanceRepository = attendanceRepository;
        this.leaveRepository = leaveRepository;
        this.payrollRepository = payrollRepository;
    }

    public Map<String, Long> getDashboardSummary() {

        Map<String, Long> summary =
                new HashMap<>();

        summary.put(
                "totalEmployees",
                userRepository.count());

        summary.put(
                "totalDepartments",
                departmentRepository.count());

        summary.put(
                "totalAttendance",
                attendanceRepository.count());

        summary.put(
                "totalLeaves",
                leaveRepository.count());

        summary.put(
                "totalPayroll",
                payrollRepository.count());

        return summary;
    }
}