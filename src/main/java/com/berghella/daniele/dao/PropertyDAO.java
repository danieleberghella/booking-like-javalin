package com.berghella.daniele.dao;

import com.berghella.daniele.model.Property;
import com.berghella.daniele.utility.DummyDatabase;

import java.util.*;

public class PropertyDAO implements GenericDAO<Property> {
    private final Map<UUID, Property> properties = DummyDatabase.getInstance().getProperties();

    @Override
    public void save(Property property) {
        properties.put(property.getId(), property);
    }

    @Override
    public void delete(UUID id) {
        properties.remove(id);
    }

    @Override
    public Optional<Property> findById(UUID id) {
        return Optional.ofNullable(properties.get(id));
    }

    @Override
    public List<Property> findAll() {
        return new ArrayList<>(properties.values());
    }
}
