package com.victor.a1.task2.service.impl;

import com.victor.a1.task2.service.Calculator;
import com.victor.a1.task2.service.ExpressionCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExpressionCalculatorImpl implements ExpressionCalculator {

    private static final int STRIVE_LIMIT = 50;
    private final Calculator calculator;

    @Override
    public double calculate(int n) {
        if (n <= 1) {
            throw new RuntimeException("Number n should be more than 1");
        }
        return calculateExpression(n);
    }

    @Override
    public boolean isStriveForZero() {
        double un = calculateExpression(2);

        for (int n = 3; n < STRIVE_LIMIT; n++) {
            double nextResult = calculateExpression(n);
            if (un < nextResult) {
                return false;
            }
            un = nextResult;
        }
        return true;
    }

    //I decided open brackets, to resolve problem with very big numbers or very little fraction
    //un = (1 / n!) * (1! + 2! + 3! + ... + n!)  ->  1!(1/n!) + 2!(1/n!) + 3(1/n!) + ... + n!(1/n!)
    private double calculateExpression(int number) {
        List<Double> firstPartExpression = calculateFirstPartExpression(number);

        double result = 0;
        for (int i = 1; i <= number; i++) {
            result += calculateTerm(firstPartExpression, i);
        }
        return result;
    }

    //I decided to split into multiples because fraction is too little for double
    private List<Double> calculateFirstPartExpression(int number) {
        List<Double> result = new ArrayList<>();
        double firstPartExpression = 1;
        int i = 0;

        for (Long factorial : calculator.calculateFactorial(number)) {
            double division = firstPartExpression / factorial.doubleValue();

            if (division != 0) {
                firstPartExpression = division;
            } else {
                result.add(firstPartExpression);
                firstPartExpression = 1;
                i = 0;
            }

            i++;
        }

        result.add(firstPartExpression);
        return result;
    }

    private double calculateTerm(List<Double> firstPartExpression, int number) {
        List<Long> factorials = calculator.calculateFactorial(number);
        double result = 1;

        for (int i = 0; i < factorials.size(); i++) {
            if (i < firstPartExpression.size()) {
                result *= factorials.get(i) * firstPartExpression.get(i);
            } else {
                result *= factorials.get(i);
            }
        }

        return result;
    }
}