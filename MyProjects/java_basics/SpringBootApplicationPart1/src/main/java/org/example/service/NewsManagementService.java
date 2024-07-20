package org.example.service;

import org.example.dto.News;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NewsManagementService implements CRUDService<News>{

    private ConcurrentHashMap<Long, News> storage = new ConcurrentHashMap<>();
    private HashMap<String, String> message = new HashMap<>();
    private static long currentId = 1;

    @Override
    public ResponseEntity getById(Long id) {
        if (!storage.containsKey(id)) {
            message.put("message", String.format("Новость с ID %d не найдена.", id));
            return new ResponseEntity(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(storage.get(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAll() {
        return new ResponseEntity(storage.values(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity create(News item) {
        Long newId = currentId++;
        item.setId(newId);
        item.setDate(Instant.now());
        storage.put(newId, item);
        return new ResponseEntity(storage.get(newId), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity update(News item) {
        item.setDate(Instant.now());
        storage.put(item.getId(), item);
        return new ResponseEntity(storage.get(item.getId()), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity delete(Long id) {
        if (!storage.containsKey(id)) {
            message.put("message", String.format("Новость с ID %d не найдена.", id));
            return new ResponseEntity(message, HttpStatus.NOT_FOUND);
        }
        storage.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
