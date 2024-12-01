package com.berghella.daniele.controller;

import com.berghella.daniele.model.Booking;
import com.berghella.daniele.model.Feedback;
import com.berghella.daniele.service.BookingService;
import com.berghella.daniele.service.FeedbackService;
import io.javalin.http.Handler;

import java.util.UUID;

public class FeedbackController {

    private static FeedbackService feedbackService = new FeedbackService();

    // http://localhost:3050/feedbacks (get)
    public static Handler getAllFeedbacks = ctx -> {
        ctx.json(feedbackService.getAllFeedbacks());
    };

    // http://localhost:3050/feedbacks (post)
    public static Handler createFeedback = ctx -> {
        Feedback feedback = ctx.bodyAsClass(Feedback.class);
        feedbackService.createFeedback(feedback);
        ctx.status(201).json(feedback);
    };

    // http://localhost:3050/feedbacks?id: (put)
    public static Handler updateFeedback = ctx -> {
        UUID feedbackId = UUID.fromString(ctx.pathParam("id"));
        Feedback feedback2Update = ctx.bodyAsClass(Feedback.class);
        if (feedbackService.updateFeedback(feedback2Update, feedbackId) != null) {
            ctx.status(200).json("Feedback successfully Updated");
        } else {
            ctx.status(404).json("Feedback not found");
        }
    };

    // http://localhost:3050/feedbacks?id:8 (delete) // queryParam
    public static Handler deleteFeedback = ctx -> {
        UUID feedbackId = UUID.fromString(ctx.queryParam("id"));
        feedbackService.deleteFeedback(feedbackId);
        ctx.status(200).json("Feedback Deleted");
    };
}
