package com.skillbox.fibonacci;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FibonacciServiceTest {
    private final FibonacciRepository repository = Mockito.mock(FibonacciRepository.class);
    private final FibonacciCalculator calculator = Mockito.mock(FibonacciCalculator.class);
    private final FibonacciService service = new FibonacciService(repository, calculator);


    @Test
    @DisplayName("Get by index from calculation")
    public void testGetByIndexFromCalculation() {
        int index = 10;
        FibonacciNumber expectedFibonacciNumber = new FibonacciNumber(10, 55);
        when(calculator.getFibonacciNumber(index)).thenReturn(55);
        FibonacciNumber actualFibonacciNumber = service.fibonacciNumber(index);
        assertEquals(expectedFibonacciNumber.getValue(), actualFibonacciNumber.getValue());
        verify(repository, times(1)).findByIndex(index);
        verify(calculator, times(1)).getFibonacciNumber(index);
    }
    @Test
    @DisplayName("Get by index from repository")
    public void testGetByIndexFromRepository() {
        int index = 10;
        FibonacciNumber expectedFibonacciNumber = new FibonacciNumber(10, 55);
        when(repository.findByIndex(index)).thenReturn(Optional.of(expectedFibonacciNumber));
        FibonacciNumber actualFibonacciNumber = service.fibonacciNumber(index);
        assertEquals(expectedFibonacciNumber.getValue(), actualFibonacciNumber.getValue());
        verify(repository, times(1)).findByIndex(index);
        verify(calculator, times(0)).getFibonacciNumber(index);
    }
    @Test
    @DisplayName("Get exception")
    public void testGetException() {
        int index = 0;
        assertThrows(IllegalArgumentException.class, () -> service.fibonacciNumber(index));
        verify(repository, times(0)).findByIndex(index);
        verify(calculator, times(0)).getFibonacciNumber(index);
    }
}
