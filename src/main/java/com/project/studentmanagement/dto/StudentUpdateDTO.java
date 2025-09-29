package com.project.studentmanagement.dto;

import com.project.studentmanagement.enums.Gender;
import com.project.studentmanagement.enums.StudentStatus;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class StudentUpdateDTO {
    
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;
    
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    
    @Email(message = "Email should be valid")
    private String email;
    
    @Size(min = 2, max = 100, message = "Department must be between 2 and 100 characters")
    private String department;
    
    @Min(value = 1, message = "Year must be at least 1")
    @Max(value = 6, message = "Year must not exceed 6")
    private Integer year;
    
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;
    
    private Gender gender;
    
    @Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
    private String address;
    
    private StudentStatus status;
    

    public StudentUpdateDTO() {}
    

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
    
    public StudentStatus getStatus() {
        return status;
    }
    
    public void setStatus(StudentStatus status) {
        this.status = status;
    }
}
