package com.skillbox.fibonacci;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FibonacciRepositoryTest extends PostgresTestContainerInitializer {

    @Autowired
    FibonacciRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EntityManager entityManager;

    public static Stream<Arguments> expectedFibonacciNumber() {
        return Stream.of(
                Arguments.of(1, 1), Arguments.of(1, 2), Arguments.of(2, 3),
                Arguments.of(3, 4), Arguments.of(5, 5), Arguments.of(8, 6),
                Arguments.of(13, 7), Arguments.of(21, 8), Arguments.of(34, 9),
                Arguments.of(55, 10)
                );
    }
    @ParameterizedTest
    @MethodSource("expectedFibonacciNumber")
    @DisplayName("Save fibonacci number")
    public void testSaveFibonacciTest(int value, int index) {
        FibonacciNumber number = new FibonacciNumber(index, value);
        repository.save(number);
        entityManager.flush();
        entityManager.detach(number);
        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index < 11",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"),
                        rs.getInt("value"))
        );
        assertEquals(number.getValue(), actual.get(0).getValue());
    }
    @Test
    @DisplayName("Get fibonacci number by index")
    public void testGetFibonacciNumberByIndex() {
        FibonacciNumber number = new FibonacciNumber(6, 8);
        repository.save(number);
        entityManager.flush();
        entityManager.detach(number);
        FibonacciNumber numberByIndex = repository.findByIndex(6).get();
        assertEquals(number.getValue(), numberByIndex.getValue());
    }
    @Test
    @DisplayName("Save same fibonacci number")
    public void testSaveSomeFibonacciNumber() {
        FibonacciNumber number1 = new FibonacciNumber(1, 1);
        repository.save(number1);
        entityManager.flush();
        entityManager.detach(number1);
        FibonacciNumber number2 = new FibonacciNumber(1, 1);
        repository.save(number2);
        entityManager.flush();
        entityManager.detach(number2);
        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 1",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"),
                        rs.getInt("value"))
        );
        assertEquals(1, actual.size());
    }
}
