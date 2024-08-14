package com.example.lms.controller;

import com.example.lms.exceptions.ErrorMessage;
import com.example.lms.models.Book;
import com.example.lms.models.Patron;
import com.example.lms.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;


}
