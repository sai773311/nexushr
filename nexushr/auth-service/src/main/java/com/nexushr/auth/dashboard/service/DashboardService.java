package com.nexushr.auth.dashboard.service;

import com.nexushr.auth.dashboard.model.DashboardSummary;
import org.springframework.stereotype.Service;
import com.nexushr.auth.employee.repository.EmployeeRepository;
import com.nexushr.auth.department.repository.DepartmentRepository;
import com.nexushr.auth.attendance.repository.AttendanceRepository;
import com.nexushr.auth.attendance.model.AttendanceStatus;
import com.nexushr.auth.leave.repository.LeaveRepository;
import com.nexushr.auth.leave.model.LeaveStatus;
import com.nexushr.auth.payroll.repository.PayrollRepository;
import com.nexushr.auth.payroll.model.PayrollStatus;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import com.nexushr.auth.recruitment.job.repository.JobRepository;
import com.nexushr.auth.recruitment.candidate.repository.CandidateRepository;
import com.nexushr.auth.recruitment.interview.repository.InterviewRepository;
import com.nexushr.auth.performance.repository.PerformanceRepository;

@Service
public class DashboardService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRepository leaveRepository;
    private final PayrollRepository payrollRepository;
    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;
    private final InterviewRepository interviewRepository;
    private final PerformanceRepository performanceRepository;

    public DashboardService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
            AttendanceRepository attendanceRepository, LeaveRepository leaveRepository, PayrollRepository payrollRepository,
            JobRepository jobRepository, CandidateRepository candidateRepository, InterviewRepository interviewRepository,
            PerformanceRepository performanceRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.attendanceRepository = attendanceRepository;
        this.leaveRepository = leaveRepository;
        this.payrollRepository = payrollRepository;
        this.jobRepository = jobRepository;
        this.candidateRepository = candidateRepository;
        this.interviewRepository = interviewRepository;
        this.performanceRepository = performanceRepository;
    }

    public DashboardSummary getDashboardSummary() {

        long totalEmployees = employeeRepository.count();
        long totalDepartments = departmentRepository.count();
        long presentToday = attendanceRepository.countByAttendanceDateAndStatus(LocalDate.now(), AttendanceStatus.PRESENT);
        long onLeaveToday = attendanceRepository.countByAttendanceDateAndStatus(LocalDate.now(), AttendanceStatus.LEAVE);
        long pendingLeaves = leaveRepository.countByStatus(LeaveStatus.PENDING);
        long pendingPayrolls = payrollRepository.countByStatus(PayrollStatus.CREATED);
        long birthdaysThisMonth = 0;

        return new DashboardSummary(
                totalEmployees,
                totalDepartments,
                presentToday,
                onLeaveToday,
                pendingLeaves,
                pendingPayrolls,
                birthdaysThisMonth
        );
    }

    public Map<String, Object> getRoleDashboard(String email, String role) {
        String normalizedRole = role == null ? "EMPLOYEE" : role.toUpperCase();
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("role", normalizedRole);
        switch (normalizedRole) {
            case "EMPLOYEE" -> {
                var attendance = attendanceRepository.findByEmployeeEmail(email);
                var leave = leaveRepository.findByEmployeeEmail(email);
                var payroll = payrollRepository.findByEmployeeEmail(email);
                var performance = performanceRepository.findByEmployeeEmail(email);
                result.put("attendanceRecords", attendance.size());
                result.put("checkedInToday", attendance.stream().anyMatch(item -> LocalDate.now().equals(item.getAttendanceDate()) && item.getCheckInTime() != null));
                result.put("leaveRequests", leave.size());
                result.put("pendingLeaveRequests", leave.stream().filter(item -> LeaveStatus.PENDING.equals(item.getStatus())).count());
                result.put("payslips", payroll.size());
                result.put("performanceGoals", performance.size());
                result.put("attendance", attendance);
                result.put("leave", leave);
                result.put("payroll", payroll);
                result.put("performance", performance);
            }
            case "MANAGER" -> {
                result.put("teamSize", employeeRepository.count());
                result.put("presentToday", attendanceRepository.countByAttendanceDateAndStatus(LocalDate.now(), AttendanceStatus.PRESENT));
                result.put("pendingLeaveApprovals", leaveRepository.countByStatus(LeaveStatus.PENDING));
                result.put("performanceReviews", performanceRepository.count());
                result.put("upcomingInterviews", interviewRepository.count());
            }
            case "HR" -> {
                result.put("employees", employeeRepository.count()); result.put("departments", departmentRepository.count());
                result.put("openJobs", jobRepository.count()); result.put("candidates", candidateRepository.count());
                result.put("interviews", interviewRepository.count()); result.put("pendingLeaves", leaveRepository.countByStatus(LeaveStatus.PENDING));
            }
            case "RECRUITER" -> {
                result.put("openPositions", jobRepository.count()); result.put("applications", candidateRepository.count());
                result.put("interviews", interviewRepository.count()); result.put("pipeline", candidateRepository.countByStatus(com.nexushr.auth.recruitment.candidate.model.CandidateStatus.SHORTLISTED));
            }
            case "FINANCE" -> {
                result.put("totalPayroll", payrollRepository.count()); result.put("pendingPayroll", payrollRepository.countByStatus(PayrollStatus.CREATED));
                result.put("processedPayroll", payrollRepository.countByStatus(PayrollStatus.PROCESSED)); result.put("paidPayroll", payrollRepository.countByStatus(PayrollStatus.PAID));
            }
            case "INTERVIEWER" -> {
                result.put("interviews", interviewRepository.count()); result.put("message", "Review assigned interviews and submit feedback.");
            }
            case "ADMIN" -> {
                result.put("users", employeeRepository.count()); result.put("employees", employeeRepository.count());
                result.put("departments", departmentRepository.count()); result.put("securityEvents", "Review audit logs and failed logins.");
            }
            default -> result.putAll(Map.of("employees", employeeRepository.count(), "departments", departmentRepository.count()));
        }
        return result;
    }
}