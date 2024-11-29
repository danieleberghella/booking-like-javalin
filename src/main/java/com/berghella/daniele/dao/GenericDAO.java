package com.berghella.daniele.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenericDAO<T> {
    void save(T entity);
    void delete(UUID id);
    Optional<T> findById(UUID id);
    List<T> findAll();
}
