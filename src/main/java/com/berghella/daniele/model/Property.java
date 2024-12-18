package com.berghella.daniele.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Property {
    private UUID id;
    private String name;
    private int rooms;
    private int beds;
    private String address;
    private String floor;
    private double pricePerNight;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startAvailableDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endAvailableDate;

}