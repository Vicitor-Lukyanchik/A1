package com.victor.a1.task1.view;

import com.victor.a1.task1.exception.NotValidIpAddressException;
import com.victor.a1.task1.service.IPv4Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final IPv4Converter converter;

    public void run() {
        int option = 1;
        while (option == 1 || option == 2) {
            option = chooseOption();
            try {
                if (option == 1) {
                    runOptionToIPv4();
                } else if (option == 2) {
                    runOptionToInt32();
                }
            } catch (NotValidIpAddressException ex) {
                System.out.println("Exception : " + ex.getMessage());
            }
        }
    }

    private int chooseOption() {
        System.out.print("Choose any option (1 - int32 to IPv4; 2 - IPv4 to int32; another number - end program) : ");
        return scanner.nextInt();
    }

    private void runOptionToIPv4() {
        System.out.println("Enter int32 number : ");
        System.out.println("Result : " + converter.convertInt32ToIPv4(scanner.nextLong()));
    }

    private void runOptionToInt32() {
        System.out.println("Enter IPv4 address(example: 128.32.10.0) : ");
        String ip = scanner.next();
        System.out.println("Result : " + converter.convertToInt32(ip));
    }
}