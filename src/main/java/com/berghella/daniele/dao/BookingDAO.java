package com.berghella.daniele.dao;

import com.berghella.daniele.model.Booking;
import com.berghella.daniele.utility.DummyDatabase;

import java.util.*;

public class BookingDAO implements GenericDAO<Booking> {
    private final Map<UUID, Booking> bookings = DummyDatabase.getInstance().getBookings();

    @Override
    public void save(Booking booking) {
        bookings.put(booking.getId(), booking);
    }

    @Override
    public void delete(UUID id) {
        bookings.remove(id);
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        return Optional.ofNullable(bookings.get(id));
    }

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(bookings.values());
    }
}