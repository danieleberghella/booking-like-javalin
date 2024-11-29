package com.berghella.daniele.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Feedback {
    private UUID id;
    private String title;
    private String text;
    private int score;
    private Booking booking; // riferimento a Booking
}
