package com.example.lms.service;

import com.example.lms.dto.PatronDTO;
import com.example.lms.exceptions.EntityNotFoundException;
import com.example.lms.models.Patron;
import com.example.lms.repository.PatronRepository;
import com.example.lms.utils.PatronMapper;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatronService implements IPatronService{
    @Autowired
    private PatronRepository patronRepository;

    @Override
    public List<PatronDTO> findAll() {
        List<Patron> patrons = patronRepository.findAll();
        return patrons.stream().map(
                PatronMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatronDTO findById(Long id) {
        Patron patron = patronRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Patron not found"));
        return PatronMapper.toDTO(patron);
    }
    @Override
    public PatronDTO savePatron(PatronDTO patronDTO){
        // TODO check if it is already exists
        Patron patron = PatronMapper.toEntity(patronDTO);
        try{
            Patron savedPatron = patronRepository.save(patron);
            return PatronMapper.toDTO(savedPatron);
        }
        catch (ConstraintViolationException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
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
    @Override
    public void deletePatron(Long id){
        // TODO cannot remove if the return date has not came yet
        if(!patronRepository.existsById(id)){
            throw new EntityNotFoundException("Patron not found");
        }
        patronRepository.deleteById(id);
    }
}
