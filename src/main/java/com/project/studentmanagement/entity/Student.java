package com.project.studentmanagement.entity;

import com.project.studentmanagement.enums.Gender;
import com.project.studentmanagement.enums.StudentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "students", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "email"),
           @UniqueConstraint(columnNames = "rollNumber")
       })
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    
    @NotBlank(message = "Roll number is required")
    @Pattern(regexp = "^[A-Z0-9]{6,12}$", message = "Roll number should be 6-12 characters with uppercase letters and numbers only")
    @Column(name = "roll_number", nullable = false, unique = true, length = 12)
    private String rollNumber;
    
    @NotBlank(message = "Department is required")
    @Size(min = 2, max = 100, message = "Department must be between 2 and 100 characters")
    @Column(name = "department", nullable = false, length = 100)
    private String department;
    
    @NotNull(message = "Year is required")
    @Min(value = 1, message = "Year must be at least 1")
    @Max(value = 6, message = "Year must not exceed 6")
    @Column(name = "year", nullable = false)
    private Integer year;
    
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dob;
    
    @NotNull(message = "Gender is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;
    
    @NotBlank(message = "Address is required")
    @Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
    @Column(name = "address", nullable = false)
    private String address;
    
    @NotNull(message = "Admission date is required")
    @Column(name = "admission_date", nullable = false)
    private LocalDate admissionDate;
    
    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StudentStatus status;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    // Default constructor
    public Student() {
        this.status = StudentStatus.PURSUING;
        this.admissionDate = LocalDate.now();
    }
    
    // Constructor with required fields
    public Student(String firstName, String lastName, String email, String rollNumber, 
                  String department, Integer year, LocalDate dob, Gender gender, String address) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rollNumber = rollNumber;
        this.department = department;
        this.year = year;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }
    

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
               Objects.equals(email, student.email) &&
               Objects.equals(rollNumber, student.rollNumber);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, email, rollNumber);
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", rollNumber='" + rollNumber + '\'' +
                ", department='" + department + '\'' +
                ", year=" + year +
                ", dob=" + dob +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", admissionDate=" + admissionDate +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}