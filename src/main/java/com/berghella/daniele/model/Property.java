package com.berghella.daniele.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Property {
    private UUID id;
    private int rooms;
    private int beds;
    private String address;
    private String floor;
    private double pricePerNight;
    private LocalDate startAvailableDate;
    private LocalDate endAvailableDate;

}