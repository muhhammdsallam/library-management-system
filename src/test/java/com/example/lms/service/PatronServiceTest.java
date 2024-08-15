package com.example.lms.service;

import com.example.lms.dto.PatronDTO;
import com.example.lms.exceptions.EntityAlreadyExistsException;
import com.example.lms.exceptions.EntityNotFoundException;
import com.example.lms.models.Patron;
import com.example.lms.repository.PatronRepository;
import com.example.lms.service.PatronService;
import com.example.lms.utils.PatronMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatronServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronService patronService;

    private Patron patron;
    private PatronDTO patronDTO;

    @BeforeEach
    public void setup() {
        patron = new Patron();
        patron.setId(1L);
        patron.setName("Test Patron");
        patron.setMobileNumber("1234567890");

        patronDTO = PatronMapper.toDTO(patron);
    }

    @Test
    public void testFindById_PatronExists() {
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));

        PatronDTO foundPatron = patronService.findById(1L);

        assertEquals("Test Patron", foundPatron.getName());
        verify(patronRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindById_PatronDoesNotExist() {
        when(patronRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> patronService.findById(1L));
        verify(patronRepository, times(1)).findById(1L);
    }

    @Test
    public void testSavePatron_NewPatron() {
        when(patronRepository.findByNameAndMobileNumber(patronDTO.getName(), patronDTO.getMobileNumber()))
                .thenReturn(Optional.empty());
        when(patronRepository.save(any(Patron.class))).thenReturn(patron);

        PatronDTO savedPatron = patronService.savePatron(patronDTO);

        assertEquals("Test Patron", savedPatron.getName());
        verify(patronRepository, times(1))
                .findByNameAndMobileNumber(patronDTO.getName(), patronDTO.getMobileNumber());
        verify(patronRepository, times(1)).save(any(Patron.class));
    }

    @Test
    public void testSavePatron_ExistingPatron() {
        when(patronRepository.findByNameAndMobileNumber(patronDTO.getName(), patronDTO.getMobileNumber()))
                .thenReturn(Optional.of(patron));

        assertThrows(EntityAlreadyExistsException.class, () -> patronService.savePatron(patronDTO));
        verify(patronRepository, times(1))
                .findByNameAndMobileNumber(patronDTO.getName(), patronDTO.getMobileNumber());
        verify(patronRepository, times(0)).save(any(Patron.class));
    }

    @Test
    public void testUpdatePatron_PatronExists() {
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));
        when(patronRepository.save(any(Patron.class))).thenReturn(patron);

        patronDTO.setName("Updated Patron");
        PatronDTO updatedPatron = patronService.updatePatron(1L, patronDTO);

        assertEquals("Updated Patron", updatedPatron.getName());
        verify(patronRepository, times(1)).findById(1L);
        verify(patronRepository, times(1)).save(any(Patron.class));
    }

    @Test
    public void testUpdatePatron_PatronDoesNotExist() {
        when(patronRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> patronService.updatePatron(1L, patronDTO));
        verify(patronRepository, times(1)).findById(1L);
        verify(patronRepository, times(0)).save(any(Patron.class));
    }

    @Test
    public void testDeletePatron_PatronExists() {
        when(patronRepository.existsById(1L)).thenReturn(true);

        patronService.deletePatron(1L);

        verify(patronRepository, times(1)).existsById(1L);
        verify(patronRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeletePatron_PatronDoesNotExist() {
        when(patronRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> patronService.deletePatron(1L));
        verify(patronRepository, times(1)).existsById(1L);
        verify(patronRepository, times(0)).deleteById(1L);
    }
}
