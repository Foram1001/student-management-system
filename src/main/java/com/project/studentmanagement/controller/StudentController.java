package com.project.studentmanagement.controller;

import com.project.studentmanagement.dto.StudentRequestDTO;
import com.project.studentmanagement.dto.StudentResponseDTO;
import com.project.studentmanagement.dto.StudentUpdateDTO;
import com.project.studentmanagement.enums.StudentStatus;
import com.project.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:8080")
public class StudentController {

    

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO requestDTO) {
        
        StudentResponseDTO response = studentService.createStudent(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        
        List<StudentResponseDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
       
        StudentResponseDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<StudentResponseDTO> getStudentByEmail(@PathVariable String email) {
       
        StudentResponseDTO student = studentService.getStudentByEmail(email);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/roll-number/{rollNumber}")
    public ResponseEntity<StudentResponseDTO> getStudentByRollNumber(@PathVariable String rollNumber) {
        
        StudentResponseDTO student = studentService.getStudentByRollNumber(rollNumber);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id,
            @Valid @RequestBody StudentUpdateDTO updateDTO) {
        
        StudentResponseDTO updatedStudent = studentService.updateStudent(id, updateDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByDepartment(@PathVariable String department) {
        
        List<StudentResponseDTO> students = studentService.getStudentsByDepartment(department);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByYear(@PathVariable Integer year) {
        
        List<StudentResponseDTO> students = studentService.getStudentsByYear(year);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByStatus(@PathVariable StudentStatus status) {
        
        List<StudentResponseDTO> students = studentService.getStudentsByStatus(status);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/department/{department}/year/{year}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByDepartmentAndYear(@PathVariable String department,
            @PathVariable Integer year) {
        List<StudentResponseDTO> students = studentService.getStudentsByDepartmentAndYear(department, year);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<StudentResponseDTO>> searchStudentsByName(@RequestParam String name) {
        
        List<StudentResponseDTO> students = studentService.searchStudentsByName(name);
        return ResponseEntity.ok(students);
    }
       

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getStudentStatistics() {
        
        Map<String, Object> statistics = studentService.getStudentStatistics();
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<StudentResponseDTO>> filterStudents(@RequestParam(required = false) String department,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) StudentStatus status) {
        
        List<StudentResponseDTO> students = studentService.filterStudents(department, year, status);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Map<String, Boolean>> checkStudentExistsByEmail(@PathVariable String email) {
        try {
            studentService.getStudentByEmail(email);
            return ResponseEntity.ok(Map.of("exists", true));
        } catch (Exception e) {
            return ResponseEntity.ok(Map.of("exists", false));
        }
    }

    @GetMapping("/exists/roll-number/{rollNumber}")
    public ResponseEntity<Map<String, Boolean>> checkStudentExistsByRollNumber(@PathVariable String rollNumber) {
        try {
            studentService.getStudentByRollNumber(rollNumber);
            return ResponseEntity.ok(Map.of("exists", true));
        } catch (Exception e) {
            return ResponseEntity.ok(Map.of("exists", false));
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getStudentCount() {
        Map<String, Object> statistics = studentService.getStudentStatistics();
        Long totalCount = (Long) statistics.get("totalStudents");
        return ResponseEntity.ok(Map.of("count", totalCount));
    }

}
