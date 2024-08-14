package com.example.lms.service;

import com.example.lms.dto.PatronDTO;
import com.example.lms.models.Patron;

import java.util.List;
import java.util.Optional;

public interface IPatronService {

    List<PatronDTO> findAll();
    PatronDTO findById(Long id);
    PatronDTO savePatron(PatronDTO patronDTO);
    PatronDTO updatePatron(Long id, PatronDTO patronDTO);
    void deletePatron(Long id);
}
