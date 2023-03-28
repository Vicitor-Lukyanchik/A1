package com.victor.a1.task3.parser;

import java.time.LocalDate;

public interface DateParser {

    LocalDate parseCsvDate(String date);

    LocalDate parseDate(String date);
}
