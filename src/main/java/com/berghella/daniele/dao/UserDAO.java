package com.berghella.daniele.dao;

import com.berghella.daniele.model.User;
import com.berghella.daniele.utility.DummyDatabase;

import java.util.*;

public class UserDAO implements GenericDAO<User> {
    private final Map<UUID, User> users = DummyDatabase.getInstance().getUsers();

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void delete(UUID id) {
        users.remove(id);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(users.get(id));
    }

    public User update(User updatedUser, UUID oldUserId){
        return users.replace(oldUserId, updatedUser);
    }

    @Override
    public List<User> findAll() {
        List<User> usersList = new ArrayList<>(users.values());
        return usersList;
    }
}