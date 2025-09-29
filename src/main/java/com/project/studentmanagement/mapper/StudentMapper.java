package com.project.studentmanagement.mapper;

import com.project.studentmanagement.dto.StudentRequestDTO;
import com.project.studentmanagement.dto.StudentResponseDTO;
import com.project.studentmanagement.dto.StudentUpdateDTO;
import com.project.studentmanagement.entity.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {
    
    
    // Convert StudentRequestDTO to Student Entity
    public Student toEntity(StudentRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        
        Student student = new Student();
        student.setFirstName(requestDTO.getFirstName());
        student.setLastName(requestDTO.getLastName());
        student.setEmail(requestDTO.getEmail());
        student.setRollNumber(requestDTO.getRollNumber());
        student.setDepartment(requestDTO.getDepartment());
        student.setYear(requestDTO.getYear());
        student.setDob(requestDTO.getDob());
        student.setGender(requestDTO.getGender());
        student.setAddress(requestDTO.getAddress());
        
        // Set default values if not provided
        student.setAdmissionDate(requestDTO.getAdmissionDate() != null ? 
            requestDTO.getAdmissionDate() : LocalDate.now());
        student.setStatus(requestDTO.getStatus() != null ? 
            requestDTO.getStatus() : student.getStatus());
        
        return student;
    }
    

    // Convert Student Entity to StudentResponseDTO
    public StudentResponseDTO toResponseDTO(Student student) {
        if (student == null) {
            return null;
        }
        
        StudentResponseDTO responseDTO = new StudentResponseDTO();
        // responseDTO.setId(student.getId());
        responseDTO.setFirstName(student.getFirstName());
        responseDTO.setLastName(student.getLastName());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNumber(student.getRollNumber());
        responseDTO.setDepartment(student.getDepartment());
        responseDTO.setYear(student.getYear());
        responseDTO.setDob(student.getDob());
        responseDTO.setGender(student.getGender());
        responseDTO.setAddress(student.getAddress());
        responseDTO.setAdmissionDate(student.getAdmissionDate());
        responseDTO.setStatus(student.getStatus());
        responseDTO.setCreatedAt(student.getCreatedAt());
        responseDTO.setUpdatedAt(student.getUpdatedAt());
        
        return responseDTO;
    }
    
    // Convert List of Student Entities to List of StudentResponseDTOs
    public List<StudentResponseDTO> toResponseDTOList(List<Student> students) {
        if (students == null) {
            return null;
        }
        
        return students.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    // Update Student Entity from StudentUpdateDTO
    public void updateEntityFromDTO(Student student, StudentUpdateDTO updateDTO) {
        if (student == null || updateDTO == null) {
            return;
        }
        
        // Only update fields that are not null in the DTO
        if (updateDTO.getFirstName() != null) {
            student.setFirstName(updateDTO.getFirstName());
        }
        if (updateDTO.getLastName() != null) {
            student.setLastName(updateDTO.getLastName());
        }
        if (updateDTO.getEmail() != null) {
            student.setEmail(updateDTO.getEmail());
        }
        if (updateDTO.getDepartment() != null) {
            student.setDepartment(updateDTO.getDepartment());
        }
        if (updateDTO.getYear() != null) {
            student.setYear(updateDTO.getYear());
        }
        if (updateDTO.getDob() != null) {
            student.setDob(updateDTO.getDob());
        }
        if (updateDTO.getGender() != null) {
            student.setGender(updateDTO.getGender());
        }
        if (updateDTO.getAddress() != null) {
            student.setAddress(updateDTO.getAddress());
        }
        if (updateDTO.getStatus() != null) {
            student.setStatus(updateDTO.getStatus());
        }
    }
    
    // Convert Student Entity to StudentUpdateDTO
    public StudentUpdateDTO toUpdateDTO(Student student) {
        if (student == null) {
            return null;
        }
        
        StudentUpdateDTO updateDTO = new StudentUpdateDTO();
        updateDTO.setFirstName(student.getFirstName());
        updateDTO.setLastName(student.getLastName());
        updateDTO.setEmail(student.getEmail());
        updateDTO.setDepartment(student.getDepartment());
        updateDTO.setYear(student.getYear());
        updateDTO.setDob(student.getDob());
        updateDTO.setGender(student.getGender());
        updateDTO.setAddress(student.getAddress());
        updateDTO.setStatus(student.getStatus());
        
        return updateDTO;
    }
}