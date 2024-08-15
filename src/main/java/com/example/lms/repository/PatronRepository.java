package com.example.lms.repository;

import com.example.lms.models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatronRepository extends JpaRepository<Patron, Long> {
    Optional<Patron> findByNameAndMobileNumber(String name, String mobileNumber);
}