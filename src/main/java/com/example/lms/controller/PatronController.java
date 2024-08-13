package com.example.lms.controller;

import com.example.lms.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;
}
