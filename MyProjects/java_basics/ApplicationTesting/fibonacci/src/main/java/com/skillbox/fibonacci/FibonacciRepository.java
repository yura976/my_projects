package com.skillbox.fibonacci;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FibonacciRepository extends CrudRepository<FibonacciNumber, Integer> {

    Optional<FibonacciNumber> findByIndex(int index);
}
