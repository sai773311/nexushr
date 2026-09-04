package com.nexushr.auth.payroll.service;

import com.nexushr.auth.payroll.model.Payroll;
import com.nexushr.auth.payroll.model.PayrollStatus;
import com.nexushr.auth.payroll.repository.PayrollRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollService {

    private final PayrollRepository payrollRepository;

    public PayrollService(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    public Payroll createPayroll(Payroll payroll) {

        double netSalary = calculateNetSalary(payroll);

        payroll.setNetSalary(netSalary);

        payroll.setStatus(PayrollStatus.CREATED);

        return payrollRepository.save(payroll);
    }

    public List<Payroll> getMyPayroll(String email) {

        return payrollRepository
                .findByEmployeeEmail(email);
    }

    public List<Payroll> getAllPayroll() {

        return payrollRepository.findAll();
    }

    public Payroll updatePayroll(
            Long id,
            Payroll updatedPayroll) {

        Payroll payroll =
                payrollRepository.findById(id)
                        .orElseThrow();

        payroll.setBasicSalary(
                updatedPayroll.getBasicSalary());

        payroll.setAllowances(
                updatedPayroll.getAllowances());

        payroll.setBonus(
                updatedPayroll.getBonus());

        payroll.setOvertime(updatedPayroll.getOvertime());
        payroll.setTax(updatedPayroll.getTax());
        payroll.setProvidentFund(updatedPayroll.getProvidentFund());
        payroll.setInsurance(updatedPayroll.getInsurance());

        payroll.setDeductions(
                updatedPayroll.getDeductions());

        payroll.setPayMonth(
                updatedPayroll.getPayMonth());

        payroll.setNetSalary(calculateNetSalary(payroll));

        return payrollRepository.save(payroll);
    }

    public Payroll verifyPayroll(Long id) {

        Payroll payroll =
                payrollRepository.findById(id)
                        .orElseThrow();

        payroll.setStatus(PayrollStatus.VERIFIED);

        return payrollRepository.save(payroll);
    }

    public Payroll approvePayroll(Long id) {

        Payroll payroll =
                payrollRepository.findById(id)
                        .orElseThrow();

        payroll.setStatus(PayrollStatus.APPROVED);

        return payrollRepository.save(payroll);
    }

    public Payroll processPayroll(Long id) {

        Payroll payroll =
                payrollRepository.findById(id)
                        .orElseThrow();

        payroll.setStatus(PayrollStatus.PROCESSED);

        return payrollRepository.save(payroll);
    }

    public Payroll releasePayroll(Long id) {

        Payroll payroll =
                payrollRepository.findById(id)
                        .orElseThrow();

        payroll.setStatus(PayrollStatus.RELEASED);

        return payrollRepository.save(payroll);
    }

    public Payroll payPayroll(Long id) {

        Payroll payroll =
                payrollRepository.findById(id)
                        .orElseThrow();

        payroll.setStatus(PayrollStatus.PAID);

        return payrollRepository.save(payroll);
    }

    public Payroll rejectPayroll(Long id) {

        Payroll payroll =
                payrollRepository.findById(id)
                        .orElseThrow();

        payroll.setStatus(PayrollStatus.REJECTED);

        return payrollRepository.save(payroll);
    }

    public void deletePayroll(Long id) {

        payrollRepository.deleteById(id);
    }

        private double calculateNetSalary(Payroll payroll) {
                double basic = payroll.getBasicSalary() == null ? 0 : payroll.getBasicSalary();
                double allowances = payroll.getAllowances() == null ? 0 : payroll.getAllowances();
                double bonus = payroll.getBonus() == null ? 0 : payroll.getBonus();
                double deductions = payroll.getDeductions() == null ? 0 : payroll.getDeductions();
                double overtime = payroll.getOvertime() == null ? 0 : payroll.getOvertime();
                double tax = payroll.getTax() == null ? 0 : payroll.getTax();
                double providentFund = payroll.getProvidentFund() == null ? 0 : payroll.getProvidentFund();
                double insurance = payroll.getInsurance() == null ? 0 : payroll.getInsurance();
                return Math.round((basic + allowances + bonus + overtime - deductions - tax - providentFund - insurance) * 100.0) / 100.0;
        }
}