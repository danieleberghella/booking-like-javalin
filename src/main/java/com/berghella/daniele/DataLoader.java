package com.berghella.daniele;

import com.berghella.daniele.model.*;
import com.berghella.daniele.service.*;

import java.time.LocalDate;
import java.util.UUID;

public class DataLoader {

    public void loadData() {
        HostService hostService = new HostService();
        PropertyService propertyService = new PropertyService();
        BookingService bookingService = new BookingService();
        FeedbackService feedbackService = new FeedbackService();
        UserService userService = new UserService();

        // Creazione di un Host
        Host host = new Host();
        host.setId(UUID.randomUUID());
        host.setName("Alice");
        host.setSurname("Johnson");
        host.setEmail("alice.johnson@example.com");
        host.setBirthDate(LocalDate.of(1985, 3, 10));
        host.setCode("H123");
        hostService.createHost(host);

        // Creazione di un User
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("John");
        user.setSurname("Doe");
        user.setEmail("john.doe@example.com");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        userService.createUser(user);

        // Creazione di una proprietà associata all'Host
        Property property = new Property();
        property.setId(UUID.randomUUID());
        property.setAddress("123 Main Street");
        property.setRooms(3);
        property.setBeds(5);
        property.setFloor("2nd");
        property.setPricePerNight(150.0);
        property.setStartAvailableDate(LocalDate.of(2024, 1, 1));
        property.setEndAvailableDate(LocalDate.of(2024, 12, 31));
        propertyService.createProperty(property);

        // Creazione di una prenotazione
        Booking booking = new Booking();
        booking.setId(UUID.randomUUID());
        booking.setStartDate(LocalDate.of(2024, 6, 1));
        booking.setEndDate(LocalDate.of(2024, 6, 7));
        booking.setUser(user);
        booking.setProperty(property);
        bookingService.createBooking(booking);

        Feedback feedback = new Feedback();
        feedback.setId(UUID.randomUUID());
        feedback.setTitle("Great stay!");
        feedback.setText("The property was amazing and very clean.");
        feedback.setScore(5);
        feedback.setBooking(booking);
        feedbackService.createFeedback(feedback);

    }
}
