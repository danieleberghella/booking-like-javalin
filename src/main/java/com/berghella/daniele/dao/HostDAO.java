package com.berghella.daniele.dao;

import com.berghella.daniele.model.Host;
import com.berghella.daniele.model.User;
import com.berghella.daniele.utility.DummyDatabase;

import java.util.*;

public class HostDAO implements GenericDAO<Host> {
    private final Map<UUID, User> users = DummyDatabase.getInstance().getUsers();

    @Override
    public void save(Host host) {
        users.put(host.getId(), host);
    }

    public Host update(Host hostUpdate, UUID oldHostId){
        if (hostUpdate instanceof Host) {
            return (Host) users.replace(oldHostId, hostUpdate);
        } else {
            throw new IllegalArgumentException("Not a Host ID.");
        }

    }

    @Override
    public void delete(UUID id) {
        User user = users.get(id);
        if (user instanceof Host) {
            users.remove(id);
        } else {
            throw new IllegalArgumentException("Not a Host ID.");
        }
    }

    @Override
    public Optional<Host> findById(UUID id) {
        User user = users.get(id);
        if (user instanceof Host) {
            return Optional.of((Host) user);
        }
        return Optional.empty();
    }

    @Override
    public List<Host> findAll() {
        List<Host> hostList = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Host) {
                hostList.add((Host) user);
            }
        }
        return hostList;
    }
}

