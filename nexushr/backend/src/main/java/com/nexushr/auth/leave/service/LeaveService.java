package com.nexushr.auth.leave.service;

import com.nexushr.auth.leave.model.Leave;
import com.nexushr.auth.leave.model.LeaveStatus;
import com.nexushr.auth.leave.repository.LeaveRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.util.Map;
import java.util.LinkedHashMap;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveService(
            LeaveRepository leaveRepository) {

        this.leaveRepository = leaveRepository;
    }

    public Leave applyLeave(
            Leave leave) {

        if (leave.getStartDate() == null || leave.getEndDate() == null || leave.getEndDate().isBefore(leave.getStartDate()))
            throw new IllegalArgumentException("Leave dates are invalid");

        leave.setStatus(
                LeaveStatus.PENDING);

        return leaveRepository.save(leave);
    }

    public List<Leave> getMyLeaves(
            String email) {

        return leaveRepository
                .findByEmployeeEmail(email);
    }

    public List<Leave> getAllLeaves() {

        return leaveRepository.findAll();
    }

    public Leave approveLeave(
            Long id) {

        Leave leave =
                leaveRepository.findById(id)
                        .orElseThrow();

        leave.setStatus(
                LeaveStatus.APPROVED);

        return leaveRepository.save(leave);
    }

    public Leave rejectLeave(
            Long id) {

        Leave leave =
                leaveRepository.findById(id)
                        .orElseThrow();

        leave.setStatus(
                LeaveStatus.REJECTED);

        return leaveRepository.save(leave);
    }

        public Leave cancelLeave(Long id, String email) {
                Leave leave = leaveRepository.findById(id).orElseThrow();
                if (!leave.getEmployeeEmail().equals(email)) throw new RuntimeException("Leave does not belong to user");
                if (leave.getStatus() != LeaveStatus.PENDING) throw new RuntimeException("Only pending leave can be cancelled");
                leave.setStatus(LeaveStatus.CANCELLED); return leaveRepository.save(leave);
        }

        public Leave extendLeave(Long id, String email, LocalDate endDate) {
                Leave leave = leaveRepository.findById(id).orElseThrow();
                if (!leave.getEmployeeEmail().equals(email) || endDate.isBefore(leave.getEndDate())) throw new RuntimeException("Invalid leave extension");
                leave.setEndDate(endDate); leave.setStatus(LeaveStatus.PENDING); return leaveRepository.save(leave);
        }

        public Map<String, Long> balance(String email) {
                Map<String, Long> result = new LinkedHashMap<>();
                result.put("approvedDays", leaveRepository.countByEmployeeEmailAndStatus(email, LeaveStatus.APPROVED));
                result.put("pendingRequests", leaveRepository.countByEmployeeEmailAndStatus(email, LeaveStatus.PENDING));
                return result;
        }
}