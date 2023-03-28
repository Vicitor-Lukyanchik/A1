package com.victor.a1.task3.parser.impl;

import com.victor.a1.task3.domain.CsvPosting;
import com.victor.a1.task3.entity.Login;
import com.victor.a1.task3.parser.CsvParser;
import com.victor.a1.task3.parser.DateParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CsvParserImpl implements CsvParser {

    private static final String LOGINS_NEXT_COLUM = ",\t";
    private static final String POSTINGS_NEXT_COLUMN = ";\t";
    private static final char OLD_DECIMAL_SEPARATOR = ',';
    private static final char NEW_DECIMAL_SEPARATOR = '.';
    private static final String STRING_BOOLEAN = "True";

    private final DateParser dateParser;


    @Override
    public List<Login> parseLogins(List<String> logins) {
        List<Login> result = new ArrayList<>();
        for (int i = 1; i < logins.size(); i++) {
            result.add(parseLogin(logins.get(i).split(LOGINS_NEXT_COLUM)));
        }
        return result;
    }

    private Login parseLogin(String[] login) {
        String application = login[0];
        String appAccountName = login[1];
        Boolean isActive = login[2].equals(STRING_BOOLEAN);
        String jobTitle = login[3];
        String department = login[4];
        return Login.builder().application(application).appAccountName(appAccountName)
                .isActive(isActive).jobTitle(jobTitle).department(department).build();
    }

    @Override
    public List<CsvPosting> parseCsvPostings(List<String> postings) {
        List<CsvPosting> result = new ArrayList<>();
        for (int i = 2; i < postings.size(); i++) {
            result.add(parsePosting(postings.get(i).split(POSTINGS_NEXT_COLUMN)));
        }
        return result;
    }

    private CsvPosting parsePosting(String[] posting) {
        String matDoc = posting[0];
        Integer item = Integer.parseInt(posting[1]);
        LocalDate docDate = dateParser.parseCsvDate(posting[2]);
        LocalDate postingDate = dateParser.parseCsvDate(posting[3]);
        String material = posting[4];
        Long quantity = Long.parseLong(posting[5]);
        String bun = posting[6];
        BigDecimal amount = parseBigDecimal(posting[7]);
        String currency = posting[8];
        String userName = posting[9];
        return CsvPosting.builder().matDoc(matDoc).item(item).docDate(docDate)
                .postingDate(postingDate).material(material).quantity(quantity)
                .bun(bun).amount(amount).currency(currency).userName(userName).build();
    }

    private BigDecimal parseBigDecimal(String decimal) {
        return new BigDecimal(decimal.replace(OLD_DECIMAL_SEPARATOR, NEW_DECIMAL_SEPARATOR));
    }
}
