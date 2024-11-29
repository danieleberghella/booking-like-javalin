package com.berghella.daniele.utility;

import com.berghella.daniele.model.Booking;
import com.berghella.daniele.model.Feedback;
import com.berghella.daniele.model.Property;
import com.berghella.daniele.model.User;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class DummyDatabase {
    private static DummyDatabase instance;

    private Map<UUID, User> users;
    private Map<UUID, Property> properties;
    private Map<UUID, Booking> bookings;
    private Map<UUID, Feedback> feedbacks;

    private DummyDatabase() {
        users = new HashMap<>();
        properties = new HashMap<>();
        bookings = new HashMap<>();
        feedbacks = new HashMap<>();
    }

    public static DummyDatabase getInstance() {
        if (instance == null) {
            instance = new DummyDatabase();
        }
        return instance;
    }
}
