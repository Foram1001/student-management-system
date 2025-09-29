package com.project.studentmanagement.dto;


import com.project.studentmanagement.enums.Gender;
import com.project.studentmanagement.enums.StudentStatus;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class StudentRequestDTO {
    
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Roll number is required")
    @Pattern(regexp = "^[A-Z0-9]{6,12}$", message = "Roll number should be 6-12 characters with uppercase letters and numbers only")
    private String rollNumber;
    
    @NotBlank(message = "Department is required")
    @Size(min = 2, max = 100, message = "Department must be between 2 and 100 characters")
    private String department;
    
    @NotNull(message = "Year is required")
    @Min(value = 1, message = "Year must be at least 1")
    @Max(value = 6, message = "Year must not exceed 6")
    private Integer year;
    
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;
    
    @NotNull(message = "Gender is required")
    private Gender gender;
    
    @NotBlank(message = "Address is required")
    @Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
    private String address;
    
    private LocalDate admissionDate;
    private StudentStatus status;
    

    public StudentRequestDTO() {
        this.status = StudentStatus.PURSUING;
        this.admissionDate = LocalDate.now();
    }
    

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
}