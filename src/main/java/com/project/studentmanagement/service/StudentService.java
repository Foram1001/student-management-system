package com.project.studentmanagement.service;

import com.project.studentmanagement.dto.StudentRequestDTO;
import com.project.studentmanagement.dto.StudentResponseDTO;
import com.project.studentmanagement.dto.StudentUpdateDTO;
import com.project.studentmanagement.entity.Student;
import com.project.studentmanagement.enums.StudentStatus;
import com.project.studentmanagement.exception.DuplicateResourceException;
import com.project.studentmanagement.exception.ResourceNotFoundException;
import com.project.studentmanagement.mapper.StudentMapper;
import com.project.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentService {

    

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    // Create a new student
    public StudentResponseDTO createStudent(StudentRequestDTO requestDTO) {
       

        // Check for duplicate email
        if (studentRepository.existsByEmail(requestDTO.getEmail())) {
            
            throw new DuplicateResourceException("Student with email '" + requestDTO.getEmail() + "' already exists");
        }

        // Check for duplicate roll number
        if (studentRepository.existsByRollNumber(requestDTO.getRollNumber())) {
            
            throw new DuplicateResourceException(
                    "Student with roll number '"  + requestDTO.getRollNumber() + "' already exists");
        }

        // Convert DTO to Entity
        Student student = studentMapper.toEntity(requestDTO);

        // Save student
        Student savedStudent = studentRepository.save(student);
        

        // Convert to response DTO and return
        return studentMapper.toResponseDTO(savedStudent);
    }

    
    // Get all students
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getAllStudents() {
        
        List<Student> students = studentRepository.findAll();
        
        return studentMapper.toResponseDTOList(students);
    }

    // Get student by ID
    @Transactional(readOnly = true)
    public StudentResponseDTO getStudentById(Long id) {
        
        Student student = findStudentById(id);
        return studentMapper.toResponseDTO(student);
    }

    // Get student by email
    @Transactional(readOnly = true)
    public StudentResponseDTO getStudentByEmail(String email) {
        
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student with email '" + email + "' not found"));
        return studentMapper.toResponseDTO(student);
    }

    // Get student by roll number
    @Transactional(readOnly = true)
    public StudentResponseDTO getStudentByRollNumber(String rollNumber) {
        
        Student student = studentRepository.findByRollNumber(rollNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Student with roll number '" + rollNumber + "' not found"));
        return studentMapper.toResponseDTO(student);
    }

    // Update student by ID
    public StudentResponseDTO updateStudent(Long id, StudentUpdateDTO updateDTO) {
       

        Student existingStudent = findStudentById(id);

        // Check for duplicate email (if email is being updated)
        if (updateDTO.getEmail() != null && !updateDTO.getEmail().equals(existingStudent.getEmail())) {
            if (studentRepository.existsByEmail(updateDTO.getEmail())) {
                
                throw new DuplicateResourceException(
                        "Student with email '" + updateDTO.getEmail() + "' already exists");
            }
        }

        // Update the entity
        studentMapper.updateEntityFromDTO(existingStudent, updateDTO);

        // Save updated student
        Student updatedStudent = studentRepository.save(existingStudent);
        

        return studentMapper.toResponseDTO(updatedStudent);
    }

    // Delete student by ID
    public void deleteStudent(Long id) {
        

        Student student = findStudentById(id);
        studentRepository.delete(student);

       
    }

    // Get students by department
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getStudentsByDepartment(String department) {
        
        List<Student> students = studentRepository.findByDepartmentIgnoreCase(department);
        
        return studentMapper.toResponseDTOList(students);
    }

    
    // Get students by year
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getStudentsByYear(Integer year) {
        
        List<Student> students = studentRepository.findByYear(year);
        
        return studentMapper.toResponseDTOList(students);
    }

    
    // Get students by status
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getStudentsByStatus(StudentStatus status) {
       
        List<Student> students = studentRepository.findByStatus(status);
       
        return studentMapper.toResponseDTOList(students);
    }

    
    // Get students by department and year
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getStudentsByDepartmentAndYear(String department, Integer year) {
        
        List<Student> students = studentRepository.findByDepartmentIgnoreCaseAndYear(department, year);
        
        return studentMapper.toResponseDTOList(students);
    }

    
    // Search students by name
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> searchStudentsByName(String name) {
        
        List<Student> students = studentRepository.findByFirstNameOrLastNameContaining(name);
        
        return studentMapper.toResponseDTOList(students);
    }

    
    // Get student statistics
    @Transactional(readOnly = true)
    public Map<String, Object> getStudentStatistics() {
       

        Map<String, Object> statistics = new HashMap<>();

        // Total count
        long totalCount = studentRepository.count();
        statistics.put("totalStudents", totalCount);

        // Count by department
        List<Object[]> departmentCounts = studentRepository.getStudentCountByDepartment();
        Map<String, Long> departmentStats = new HashMap<>();
        for (Object[] row : departmentCounts) {
            departmentStats.put((String) row[0], (Long) row[1]);
        }
        statistics.put("byDepartment", departmentStats);

        // Count by year
        List<Object[]> yearCounts = studentRepository.getStudentCountByYear();
        Map<Integer, Long> yearStats = new HashMap<>();
        for (Object[] row : yearCounts) {
            yearStats.put((Integer) row[0], (Long) row[1]);
        }
        statistics.put("byYear", yearStats);

        // Count by status
        List<Object[]> statusCounts = studentRepository.getStudentCountByStatus();
        Map<String, Long> statusStats = new HashMap<>();
        for (Object[] row : statusCounts) {
            statusStats.put(row[0].toString(), (Long) row[1]);
        }
        statistics.put("byStatus", statusStats);

        
        return statistics;
    }

    
    // Filter students by multiple criteria
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> filterStudents(String department, Integer year, StudentStatus status) {
        
        List<Student> students = studentRepository.findStudentsByFilters(department, year, status);
        
        return studentMapper.toResponseDTOList(students);
    }

    // Helper method to find student by ID
    private Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID '" + id + "' not found"));
    }
}