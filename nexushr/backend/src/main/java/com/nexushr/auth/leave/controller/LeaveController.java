package com.nexushr.auth.leave.controller;

import com.nexushr.auth.leave.model.Leave;
import com.nexushr.auth.leave.service.LeaveService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(
            LeaveService leaveService) {

        this.leaveService = leaveService;
    }

    @PostMapping("/apply")
    public Leave applyLeave(
            @RequestBody Leave leave,
            Authentication authentication) {

        leave.setEmployeeEmail(
                authentication.getName());

        return leaveService.applyLeave(leave);
    }

    @GetMapping("/my-leaves")
    public Object myLeaves(
            Authentication authentication) {

        return leaveService.getMyLeaves(
                authentication.getName());
    }

    @GetMapping("/all")
    public Object allLeaves() {

        return leaveService.getAllLeaves();
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAnyRole('MANAGER', 'HR', 'ADMIN')")
    public Leave approveLeave(
            @PathVariable Long id) {

        return leaveService.approveLeave(id);
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasAnyRole('MANAGER', 'HR', 'ADMIN')")
    public Leave rejectLeave(
            @PathVariable Long id) {

        return leaveService.rejectLeave(id);
    }

    @PutMapping("/{id}/cancel")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public Leave cancel(@PathVariable Long id, Authentication authentication) { return leaveService.cancelLeave(id, authentication.getName()); }

    @PutMapping("/{id}/extend")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public Leave extend(@PathVariable Long id, @RequestParam LocalDate endDate, Authentication authentication) { return leaveService.extendLeave(id, authentication.getName(), endDate); }

    @GetMapping("/balance")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public Object balance(Authentication authentication) { return leaveService.balance(authentication.getName()); }
}