package com.example.lms.controller;

import com.example.lms.dto.BookDTO;
import com.example.lms.dto.PatronDTO;
import com.example.lms.exceptions.ErrorMessage;
import com.example.lms.models.Book;
import com.example.lms.models.Patron;
import com.example.lms.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;

    @GetMapping
    public ResponseEntity<List<PatronDTO>> getAllPatrons(){
        List<PatronDTO> patronDTOS = patronService.findAll();
        return new ResponseEntity<>(patronDTOS, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<PatronDTO> getPatronById(@PathVariable Long id) {
        PatronDTO patronDTO = patronService.findById(id);
        return new ResponseEntity<>(patronDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<PatronDTO> addNewPatron(@Valid @RequestBody PatronDTO patronDTO){
        PatronDTO addedPatronDTO = patronService.savePatron(patronDTO);
        return new ResponseEntity<>(addedPatronDTO, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatronDTO> updatePatron(@PathVariable Long id,@Valid @RequestBody PatronDTO patronDTO ){
        PatronDTO updatedPatronDTO = patronService.updatePatron(id, patronDTO);
        return new ResponseEntity<>(updatedPatronDTO, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        patronService.deletePatron(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
