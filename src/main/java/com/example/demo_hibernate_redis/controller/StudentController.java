package com.example.demo_hibernate_redis.controller;

import com.example.demo_hibernate_redis.model.Student;
import com.example.demo_hibernate_redis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllStudent(){
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable Long studentId) {
        return new ResponseEntity<>(studentService.getById(studentId), HttpStatus.OK);
    }

    @PutMapping("{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable Long studentId,
                                            @RequestBody Student student) {
        return new ResponseEntity<>(studentService.updateById(studentId, student), HttpStatus.OK);
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.deleteEmployee(studentId);
    }
}
