package com.skillbox.fibonacci;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibonacciController {

    private final FibonacciService service;

    public FibonacciController(FibonacciService service) {
        this.service = service;
    }

    @GetMapping("/fibonacci/{index}")
    public ResponseEntity getNumber(@PathVariable int index) {
        try {
            FibonacciNumber number = service.fibonacciNumber(index);
            return ResponseEntity.ok(number);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
