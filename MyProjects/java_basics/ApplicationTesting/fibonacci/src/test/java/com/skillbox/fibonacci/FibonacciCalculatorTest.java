package com.skillbox.fibonacci;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FibonacciCalculatorTest {
    private final FibonacciCalculator calculator = new FibonacciCalculator();
    public static Stream<Arguments> expectedFibonacciNumber() {
        return Stream.of(
                Arguments.of(1, 1), Arguments.of(1, 2), Arguments.of(2, 3),
                Arguments.of(3, 4), Arguments.of(5, 5), Arguments.of(8, 6),
                Arguments.of(13, 7), Arguments.of(21, 8), Arguments.of(34, 9),
                Arguments.of(55, 10), Arguments.of(89, 11), Arguments.of(144, 12),
                Arguments.of(233, 13), Arguments.of(377, 14) , Arguments.of(610, 15));
    }
    @ParameterizedTest
    @DisplayName("Get Fibonacci number")
    @MethodSource("expectedFibonacciNumber")
    public void testGetFibonacciNumber(Integer expectedFibonacciNumber, int index) {
        assertEquals(expectedFibonacciNumber, calculator.getFibonacciNumber(index));
    }
    public static Stream<Arguments> expectedException() {
        return Stream.of(
                Arguments.of(0), Arguments.of(-1)
        );
    }
    @ParameterizedTest
    @DisplayName("Get exception")
    @MethodSource("expectedException")
    public void testGetException(int index) {
        assertThrows(IllegalArgumentException.class, () -> calculator.getFibonacciNumber(index));
    }
}
