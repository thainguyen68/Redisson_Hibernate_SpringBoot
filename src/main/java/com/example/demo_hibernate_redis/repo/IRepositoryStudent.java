package com.example.demo_hibernate_redis.repo;

import com.example.demo_hibernate_redis.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryStudent extends JpaRepository<Student, Long> {
}
