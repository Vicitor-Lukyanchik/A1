package com.victor.a1.task3.parser.impl;

import com.victor.a1.task3.parser.DateParser;
import com.victor.a1.task3.parser.Validator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateParserImpl implements DateParser {

    private static final DateTimeFormatter CSV_DATE_FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate parseCsvDate(String date) {
        Validator.validateDate(date);
        return LocalDate.parse(date, CSV_DATE_FORMATTER);
    }

    @Override
    public LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }
}
