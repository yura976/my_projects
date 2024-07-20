package com.skillbox.fibonacci;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FibonacciService {

    private final FibonacciRepository repository;
    private final FibonacciCalculator calculator;

    public FibonacciService(FibonacciRepository repository, FibonacciCalculator calculator) {
        this.repository = repository;
        this.calculator = calculator;
    }

    public FibonacciNumber fibonacciNumber(int index) {
        if (index < 1) {
            throw new IllegalArgumentException("Index should be greater or equal to 1");
        }

        Optional<FibonacciNumber> loadedNumber = repository.findByIndex(index);
        if (loadedNumber.isPresent()) {
            return loadedNumber.get();
        }

        FibonacciNumber computedNumber = new FibonacciNumber(index, calculator.getFibonacciNumber(index));

        repository.save(computedNumber);

        return computedNumber;
    }

}
