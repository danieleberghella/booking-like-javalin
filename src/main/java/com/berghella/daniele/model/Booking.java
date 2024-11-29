package com.berghella.daniele.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Booking {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private User user;
    private Property property;
}
