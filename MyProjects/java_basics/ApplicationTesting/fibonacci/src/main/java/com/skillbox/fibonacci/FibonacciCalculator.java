package com.skillbox.fibonacci;

import org.springframework.stereotype.Service;

@Service
public class FibonacciCalculator {

    public Integer getFibonacciNumber(int index) {
        //Порядковый номер числа не может быть меньше единицы
        if (index < 1) {
            throw new IllegalArgumentException("Index should be greater or equal to 1");
        }
        //Первые два числа в последовательности равны 1
        if (index == 1 || index == 2) {
            return 1;
        }
        //Чтобы найти следующее число в последовательности, нужно сложить значение двух предыдущих
        return getFibonacciNumber(index - 1) + getFibonacciNumber(index - 2);
    }
}
