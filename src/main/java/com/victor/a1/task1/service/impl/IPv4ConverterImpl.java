package com.victor.a1.task1.service.impl;

import com.victor.a1.task1.exception.NotValidIpAddressException;
import com.victor.a1.task1.service.IPv4Converter;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class IPv4ConverterImpl implements IPv4Converter {

    private static final String POINT = ".";
    private static final String POINT_REGEX_IPV4_ADDRESS = "\\.";
    private static final String IPV4_ADDRESS_REGEX = "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])" + "\\."
            + "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])" + "\\."
            + "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])" + "\\."
            + "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])";

    @Override
    public long convertToInt32(String ipAddress) {
        validateIPv4Address(ipAddress);
        long result = 0;
        String[] ipAddressInArray = ipAddress.split(POINT_REGEX_IPV4_ADDRESS);

        for (int i = 3; i >= 0; i--) {
            long ip = Long.parseLong(ipAddressInArray[3 - i]);
            result |= ip << (i * 8);
        }
        return result;
    }

    private void validateIPv4Address(String ipAddress) {
        Pattern ip_pattern = Pattern.compile(IPV4_ADDRESS_REGEX);
        if (!ip_pattern.matcher(ipAddress).matches()) {
            throw new NotValidIpAddressException("Invalid ip address (example : 127.0.0.1)");
        }
    }

    @Override
    public String convertInt32ToIPv4(long ip) {
        return ((ip >> 24) & 0xFF) + POINT +
                ((ip >> 16) & 0xFF) + POINT +
                ((ip >> 8) & 0xFF) + POINT +
                (ip & 0xFF);
    }
}
