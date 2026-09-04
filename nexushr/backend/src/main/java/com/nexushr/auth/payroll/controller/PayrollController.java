package com.nexushr.auth.payroll.controller;

import com.nexushr.auth.payroll.model.Payroll;
import com.nexushr.auth.payroll.service.PayrollService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(
            PayrollService payrollService) {

        this.payrollService = payrollService;
    }

    @PostMapping("/create")
    public Payroll createPayroll(
            @RequestBody Payroll payroll) {

        return payrollService.createPayroll(payroll);
    }

    @GetMapping("/my-payroll")
    public Object myPayroll(
            Authentication authentication) {

        return payrollService.getMyPayroll(
                authentication.getName());
    }

    @GetMapping("/all")
    public Object allPayroll() {

        return payrollService.getAllPayroll();
    }

    @PutMapping("/{id}")
    public Payroll updatePayroll(
            @PathVariable Long id,
            @RequestBody Payroll payroll) {

        return payrollService.updatePayroll(
                id,
                payroll);
    }

    @PutMapping("/{id}/verify")
    public Payroll verifyPayroll(
            @PathVariable Long id) {

        return payrollService.verifyPayroll(id);
    }

    @PutMapping("/{id}/approve")
    public Payroll approvePayroll(
            @PathVariable Long id) {

        return payrollService.approvePayroll(id);
    }

    @PutMapping("/{id}/process")
    public Payroll processPayroll(
            @PathVariable Long id) {

        return payrollService.processPayroll(id);
    }

    @PutMapping("/{id}/release")
    public Payroll releasePayroll(
            @PathVariable Long id) {

        return payrollService.releasePayroll(id);
    }

    @PutMapping("/{id}/pay")
    public Payroll payPayroll(
            @PathVariable Long id) {

        return payrollService.payPayroll(id);
    }

    @PutMapping("/{id}/reject")
    public Payroll rejectPayroll(
            @PathVariable Long id) {

        return payrollService.rejectPayroll(id);
    }

    @DeleteMapping("/{id}")
    public String deletePayroll(
            @PathVariable Long id) {

        payrollService.deletePayroll(id);

        return "Payroll Deleted Successfully";
    }
}