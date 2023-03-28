package com.victor.a1.task2.view;

import com.victor.a1.task2.service.ExpressionCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final ExpressionCalculator calculator;

    public void run() {
        int option = 1;
        while (option == 1 || option == 2) {
            option = chooseOption();

            try {
                if (option == 1) {
                    runCalculateExpression();
                } else if (option == 2) {
                    runCheckStrive();
                }
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private int chooseOption() {
        System.out.print("Choose any option (1 - enter n; 2 - is strive for zero; another number - end program) : ");
        return scanner.nextInt();
    }

    private void runCalculateExpression() {
        System.out.print("Enter n (n > 1) : ");
        int n = scanner.nextInt();
        System.out.println("Result : " + calculator.calculate(n));
    }

    private void runCheckStrive() {
        if (calculator.isStriveForZero()) {
            System.out.println("This expression strive for zero");
        }
    }
}
