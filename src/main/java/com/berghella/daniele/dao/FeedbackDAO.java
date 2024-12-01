package com.berghella.daniele.dao;

import com.berghella.daniele.model.Feedback;
import com.berghella.daniele.model.User;
import com.berghella.daniele.utility.DummyDatabase;

import java.util.*;

public class FeedbackDAO implements GenericDAO<Feedback> {
    private final Map<UUID, Feedback> feedbacks = DummyDatabase.getInstance().getFeedbacks();

    @Override
    public void save(Feedback feedback) {
        feedbacks.put(feedback.getId(), feedback);
    }

    public Feedback update(Feedback updatedFeedback, UUID oldFeedbackId){
        return feedbacks.replace(oldFeedbackId, updatedFeedback);
    }

    @Override
    public void delete(UUID id) {
        feedbacks.remove(id);
    }

    @Override
    public Optional<Feedback> findById(UUID id) {
        return Optional.ofNullable(feedbacks.get(id));
    }

    @Override
    public List<Feedback> findAll() {
        return new ArrayList<>(feedbacks.values());
    }
}