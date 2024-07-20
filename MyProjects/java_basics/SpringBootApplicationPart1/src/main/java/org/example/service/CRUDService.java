package org.example.service;

import org.springframework.http.ResponseEntity;

public interface CRUDService<T> {
    ResponseEntity getById(Long id);
    ResponseEntity getAll();
    ResponseEntity create(T item);
    ResponseEntity update(T item);
    ResponseEntity delete(Long id);
}
