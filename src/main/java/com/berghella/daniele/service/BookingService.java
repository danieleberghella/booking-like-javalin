package com.berghella.daniele.service;

import com.berghella.daniele.dao.BookingDAO;
import com.berghella.daniele.model.Booking;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookingService {
    private final BookingDAO bookingDAO = new BookingDAO();

    public void createBooking(Booking booking) {
        bookingDAO.save(booking);
    }

    public Optional<Booking> getBookingById(UUID id) {
        return bookingDAO.findById(id);
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.findAll();
    }

    public void deleteBooking(UUID id) {
        bookingDAO.delete(id);
    }

    /* Funzionalità aggiuntive */
    public Optional<Booking> getLastBookingByUserId(UUID userId) {
        List<Booking> allBookings = bookingDAO.findAll();
        Booking lastBooking = null;
        for (Booking booking : allBookings) {
            if (booking.getUser().getId().equals(userId)) {
                if (lastBooking == null || booking.getStartDate().isAfter(lastBooking.getStartDate())) {
                    lastBooking = booking;
                }
            }
        }
        return Optional.ofNullable(lastBooking);
    }
}