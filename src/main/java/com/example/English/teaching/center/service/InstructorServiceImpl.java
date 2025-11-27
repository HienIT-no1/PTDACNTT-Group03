package com.example.English.teaching.center.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.English.teaching.center.model.Instructor;
import com.example.English.teaching.center.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllIntructors() {
        return instructorRepository.findAll();
    }
    
}
