package com.nexushr.auth.employee.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String employeeId;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    private String department;

    private String designation;

    private String address;

    private String emergencyContactName;
    private String emergencyContactPhone;
    private String emergencyContactRelation;
    private String bankAccountNumber;
    private String bankName;
    private String bankIfscCode;
    private Long managerId;
    private String employmentType;
    private String lastAction;
    private LocalDate actionDate;
    private String actionReason;

    private LocalDate joiningDate;

    private Double salary;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public String getEmergencyContactName() { return emergencyContactName; }
    public void setEmergencyContactName(String value) { emergencyContactName = value; }
    public String getEmergencyContactPhone() { return emergencyContactPhone; }
    public void setEmergencyContactPhone(String value) { emergencyContactPhone = value; }
    public String getEmergencyContactRelation() { return emergencyContactRelation; }
    public void setEmergencyContactRelation(String value) { emergencyContactRelation = value; }
    public String getBankAccountNumber() { return bankAccountNumber; }
    public void setBankAccountNumber(String value) { bankAccountNumber = value; }
    public String getBankName() { return bankName; }
    public void setBankName(String value) { bankName = value; }
    public String getBankIfscCode() { return bankIfscCode; }
    public void setBankIfscCode(String value) { bankIfscCode = value; }
    public Long getManagerId() { return managerId; }
    public void setManagerId(Long value) { managerId = value; }
    public String getEmploymentType() { return employmentType; }
    public void setEmploymentType(String value) { employmentType = value; }
    public String getLastAction() { return lastAction; }
    public void setLastAction(String value) { lastAction = value; }
    public LocalDate getActionDate() { return actionDate; }
    public void setActionDate(LocalDate value) { actionDate = value; }
    public String getActionReason() { return actionReason; }
    public void setActionReason(String value) { actionReason = value; }
}