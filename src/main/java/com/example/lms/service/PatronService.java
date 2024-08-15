package com.example.lms.service;

import com.example.lms.dto.PatronDTO;
import com.example.lms.exceptions.EntityAlreadyExistsException;
import com.example.lms.exceptions.EntityNotFoundException;
import com.example.lms.models.Patron;
import com.example.lms.repository.PatronRepository;
import com.example.lms.utils.PatronMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatronService implements IPatronService{
    @Autowired
    private PatronRepository patronRepository;

    @Transactional
    @Override
    public List<PatronDTO> findAll() {
        List<Patron> patrons = patronRepository.findAll();
        return patrons.stream().map(
                PatronMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PatronDTO findById(Long id) {
        Patron patron = patronRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Patron not found"));
        return PatronMapper.toDTO(patron);
    }
    @Transactional
    @Override
    public PatronDTO savePatron(PatronDTO patronDTO){
        Optional<Patron> existingPatron = patronRepository.findByNameAndMobileNumber(
                patronDTO.getName(), patronDTO.getMobileNumber());
        if(existingPatron.isPresent()){
            throw new EntityAlreadyExistsException("patron already exists");
        }

        Patron patron = PatronMapper.toEntity(patronDTO);
        try{
            Patron savedPatron = patronRepository.save(patron);
            return PatronMapper.toDTO(savedPatron);
        }
        catch (ConstraintViolationException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    @Transactional
    @Override
    public PatronDTO updatePatron(Long id, PatronDTO patronDTO){
        try {
            Patron existingPatron = patronRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Patron not found"));
            existingPatron.setName(patronDTO.getName());
            existingPatron.setMobileNumber(patronDTO.getMobileNumber());

            Patron updatedPatron = patronRepository.save(existingPatron);
            return PatronMapper.toDTO(updatedPatron);
        }
        catch (ConstraintViolationException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    @Transactional
    @Override
    public void deletePatron(Long id){
        // TODO cannot remove if the return date has not came yet
        if(!patronRepository.existsById(id)){
            throw new EntityNotFoundException("Patron not found");
        }
        patronRepository.deleteById(id);
    }
}
