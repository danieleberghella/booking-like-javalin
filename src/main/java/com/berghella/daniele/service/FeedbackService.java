package com.berghella.daniele.service;

import com.berghella.daniele.dao.FeedbackDAO;
import com.berghella.daniele.model.Feedback;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FeedbackService {
    private final FeedbackDAO feedbackDAO = new FeedbackDAO();

    public void createFeedback(Feedback feedback) {
        feedbackDAO.save(feedback);
    }

    public Optional<Feedback> getFeedbackById(UUID id) {
        return feedbackDAO.findById(id);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackDAO.findAll();
    }

    public void deleteFeedback(UUID id) {
        feedbackDAO.delete(id);
    }
}