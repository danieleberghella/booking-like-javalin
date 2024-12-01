package com.berghella.daniele.controller;

import com.berghella.daniele.model.Booking;
import com.berghella.daniele.model.Property;
import com.berghella.daniele.service.BookingService;
import com.berghella.daniele.service.PropertyService;
import io.javalin.http.Handler;

import java.util.UUID;

public class BookingController {

    private static BookingService bookingService = new BookingService();

    // http://localhost:3050/bookings (get)
    public static Handler getAllBookings = ctx -> {
        ctx.json(bookingService.getAllBookings());
    };

    // http://localhost:3050/bookings (post)
    public static Handler createBooking = ctx -> {
        Booking booking = ctx.bodyAsClass(Booking.class);
        bookingService.createBooking(booking);
        ctx.status(201).json(booking);
    };

    // http://localhost:3050/bookings?id: (put)
    public static Handler updateBooking = ctx -> {
        UUID bookingId = UUID.fromString(ctx.pathParam("id"));
        Booking booking2Update = ctx.bodyAsClass(Booking.class);
        if (bookingService.updateBooking(booking2Update, bookingId) != null) {
            ctx.status(200).json("Booking successfully Updated");
        } else {
            ctx.status(404).json("Booking not found");
        }
    };

    // http://localhost:3050/bookings?id:8 (delete) // queryParam
    public static Handler deleteBooking = ctx -> {
        UUID bookingId = UUID.fromString(ctx.queryParam("id"));
        bookingService.deleteBooking(bookingId);
        ctx.status(200).json("Booking Deleted");
    };
}
