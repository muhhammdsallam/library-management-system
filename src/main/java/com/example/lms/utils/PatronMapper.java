package com.example.lms.utils;

import com.example.lms.dto.PatronDTO;
import com.example.lms.models.Patron;

public class PatronMapper {

    public static PatronDTO toDTO(Patron patron) {
        PatronDTO patronDTO = new PatronDTO();
        patronDTO.setId(patron.getId());
        patronDTO.setName(patron.getName());
        patronDTO.setMobileNumber(patron.getMobileNumber());
        return patronDTO;
    }

    public static Patron toEntity(PatronDTO patronDTO) {
        Patron patron = new Patron();
        patron.setId(patronDTO.getId());
        patron.setName(patronDTO.getName());
        patron.setMobileNumber(patronDTO.getMobileNumber());
        return patron;
    }
}
