package com.example.lms.service;

import com.example.lms.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatronService implements IPatronService{
    @Autowired
    private PatronRepository patronRepository;
}
