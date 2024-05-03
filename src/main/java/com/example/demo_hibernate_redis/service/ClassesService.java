package com.example.demo_hibernate_redis.service;

import com.example.demo_hibernate_redis.controller.ResourceNotFoundException;
import com.example.demo_hibernate_redis.model.Classes;
import com.example.demo_hibernate_redis.repo.IRepositoryClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassesService {
    private final IRepositoryClasses repositoryClasses;

    @Autowired
    public ClassesService(IRepositoryClasses repositoryClasses) {
        this.repositoryClasses = repositoryClasses;
    }

    public List<Classes> getAllClasses(){
       return repositoryClasses.findAll();
    }

    public Classes getById(Long classesId) {
        return repositoryClasses.findById(classesId)
                .orElseThrow(() -> new ResourceNotFoundException("Classes not found for this id :: " + classesId));
    }

}
