package com.victor.a1.task3.parser;

import com.victor.a1.task3.exception.ValidatorParserException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Validator {

    private static final String REGEX_DATE = "\\d{2}+[.]\\d{2}+[.]\\d{4}";

    private Validator() {
    }

    public static void validateDate(String date) {
        if (!date.matches(REGEX_DATE)) {
            String message = "Date should be this format : DD.MM.YYYY";
            log.error(message);
            throw new ValidatorParserException(message);
        }
    }
}
