package com.example.demo_hibernate_redis.service;

import com.example.demo_hibernate_redis.controller.ResourceNotFoundException;
import com.example.demo_hibernate_redis.model.Student;
import com.example.demo_hibernate_redis.repo.IRepositoryStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    private final IRepositoryStudent repositoryStudent;

    @Autowired
    public StudentService(IRepositoryStudent repositoryStudent) {
        this.repositoryStudent = repositoryStudent;
    }

    @Transactional
    public Student addStudent(Student student) {
        Student studentS = repositoryStudent.save(student);
        return repositoryStudent.findById(studentS.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Student not found for this id :: " + studentS.getId())
        );
    }

    @Cacheable(value = "student")
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return repositoryStudent.findAll();
    }

    @Transactional(readOnly = true)
    public Student getById(Long studentId) {
        return repositoryStudent.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
    }

    @Transactional
    public Student updateById(Long studentId, Student student) {
        Student studentS = repositoryStudent.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
        studentS.setName(student.getName());
        studentS.setClasses(student.getClasses());
        return repositoryStudent.save(studentS);
    }

    @Transactional
    public void deleteEmployee(Long studentId) {
        Student student = repositoryStudent.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student not found" + studentId));
        repositoryStudent.delete(student);
    }
}
