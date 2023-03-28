package com.victor.a1.task1.service;

public interface IPv4Converter {

    long convertToInt32(String ipAddress);

    String convertInt32ToIPv4(long ip);
}
