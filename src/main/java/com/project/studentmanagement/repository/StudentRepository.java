package com.project.studentmanagement.repository;

import com.project.studentmanagement.entity.Student;
import com.project.studentmanagement.enums.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    // Find student by email
    Optional<Student> findByEmail(String email);
    
    // Find student by roll number
    Optional<Student> findByRollNumber(String rollNumber);
    
    // Check if email exists
    boolean existsByEmail(String email);
    
    // Check if roll number exists
    boolean existsByRollNumber(String rollNumber);
    
    // Find students by department
    List<Student> findByDepartmentIgnoreCase(String department);
    
    // Find students by year
    List<Student> findByYear(Integer year);
    
    // Find students by status
    List<Student> findByStatus(StudentStatus status);
    
    // Find students by department and year
    List<Student> findByDepartmentIgnoreCaseAndYear(String department, Integer year);
    
    // Find students by first name or last name (case insensitive)
    @Query("SELECT s FROM Student s WHERE " +
           "LOWER(s.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
           "LOWER(s.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Student> findByFirstNameOrLastNameContaining(@Param("name") String name);
    
    // Get count by department
    @Query("SELECT s.department, COUNT(s) FROM Student s GROUP BY s.department")
    List<Object[]> getStudentCountByDepartment();
    
    // Get count by year
    @Query("SELECT s.year, COUNT(s) FROM Student s GROUP BY s.year ORDER BY s.year")
    List<Object[]> getStudentCountByYear();
    
    // Get count by status
    @Query("SELECT s.status, COUNT(s) FROM Student s GROUP BY s.status")
    List<Object[]> getStudentCountByStatus();
    
    // Find students by department and status
    List<Student> findByDepartmentIgnoreCaseAndStatus(String department, StudentStatus status);
    
    // Find students by year and status
    List<Student> findByYearAndStatus(Integer year, StudentStatus status);
    
    // Custom query to find students with pagination support
    @Query("SELECT s FROM Student s WHERE " +
           "(:department IS NULL OR LOWER(s.department) = LOWER(:department)) AND " +
           "(:year IS NULL OR s.year = :year) AND " +
           "(:status IS NULL OR s.status = :status)")
    List<Student> findStudentsByFilters(@Param("department") String department,
                                        @Param("year") Integer year,
                                        @Param("status") StudentStatus status);
}