package com.project.studentmanagement.dto;

import com.project.studentmanagement.enums.Gender;
import com.project.studentmanagement.enums.StudentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StudentResponseDTO {
    
    // private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String rollNumber;
    private String department;
    private Integer year;
    private LocalDate dob;
    private Gender gender;
    private String address;
    private LocalDate admissionDate;
    private StudentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    

    public StudentResponseDTO() {}
    
    // Constructor for mapping from entity
    public StudentResponseDTO(Long id, String firstName, String lastName,
                             String email, String rollNumber, String department, Integer year,
                             LocalDate dob, Gender gender, String address, LocalDate admissionDate,
                             StudentStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        // this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rollNumber = rollNumber;
        this.department = department;
        this.year = year;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.admissionDate = admissionDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    

    // public Long getId() {
    //     return id;
    // }
    
    // public void setId(Long id) {
    //     this.id = id;
    // }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRollNumber() {
        return rollNumber;
    }
    
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public Integer getYear() {
        return year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    public LocalDate getDob() {
        return dob;
    }
    
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    
    public Gender getGender() {
        return gender;
    }
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public LocalDate getAdmissionDate() {
        return admissionDate;
    }
    
    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }
    
    public StudentStatus getStatus() {
        return status;
    }
    
    public void setStatus(StudentStatus status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}