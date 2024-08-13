package com.example.lms.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "patron")
@Data
@NoArgsConstructor
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "patron name cannot be null")
    private String name;

    @NotNull(message = "mobile number cannot be null")
    @Size(min = 10, max = 15, message = "Mobile number must be between 10 and 15 characters")
    @Pattern(regexp = "\\d+", message = "Mobile number must be numeric")
    @Column(length = 15)
    private String mobileNumber;

}
