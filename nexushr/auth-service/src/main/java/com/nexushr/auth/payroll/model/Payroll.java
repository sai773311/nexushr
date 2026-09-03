package com.nexushr.auth.payroll.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payroll")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeEmail;

    private Double basicSalary;

    private Double allowances;

    private Double bonus;

    private Double deductions;
    private Double overtime;
    private Double tax;
    private Double providentFund;
    private Double insurance;

    private Double netSalary;

    private String payMonth;

    @Enumerated(EnumType.STRING)
    private PayrollStatus status;

    public Payroll() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Double getAllowances() {
        return allowances;
    }

    public void setAllowances(Double allowances) {
        this.allowances = allowances;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Double getDeductions() {
        return deductions;
    }

    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public PayrollStatus getStatus() {
        return status;
    }

    public void setStatus(PayrollStatus status) {
        this.status = status;
    }

    public Double getOvertime() { return overtime; }
    public void setOvertime(Double value) { overtime = value; }
    public Double getTax() { return tax; }
    public void setTax(Double value) { tax = value; }
    public Double getProvidentFund() { return providentFund; }
    public void setProvidentFund(Double value) { providentFund = value; }
    public Double getInsurance() { return insurance; }
    public void setInsurance(Double value) { insurance = value; }
}