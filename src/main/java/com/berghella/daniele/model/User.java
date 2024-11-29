package com.berghella.daniele.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
}
