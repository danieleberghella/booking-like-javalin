package com.berghella.daniele.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
