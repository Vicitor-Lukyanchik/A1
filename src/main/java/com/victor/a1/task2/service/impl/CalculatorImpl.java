package com.victor.a1.task2.service.impl;

import com.victor.a1.task2.exception.NotNegativeFactorialNumberException;
import com.victor.a1.task2.service.Calculator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CalculatorImpl implements Calculator {

    //I decided to split into multiples because after 20! number is too big for long
    private static final int MAX_LONG_DIGIT_NUMBER = 19;
    @Override
    public List<Long> calculateFactorial(int number) {
        validateNumber(number);
        List<Long> factorials = new ArrayList<>();
        long factorial = 1;

        for (int i = 2; i <= number; i++) {
            if (getSumDigitsInNumbers(number, factorial) < MAX_LONG_DIGIT_NUMBER) {
                factorial *= i;
            } else {
                factorials.add(factorial);
                factorial = i;
            }
        }

        factorials.add(factorial);
        return factorials;
    }

    private void validateNumber(int number) {
        if (number < 0) {
            throw new NotNegativeFactorialNumberException("Factorial does not calculate for negative numbers");
        }
    }
    private static int getSumDigitsInNumbers(int number, long factorial) {
        return String.valueOf(number).length() + String.valueOf(factorial).length();
    }
}
