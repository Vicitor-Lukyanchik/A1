package com.victor.a1.task3.parser;

import com.victor.a1.task3.domain.CsvPosting;
import com.victor.a1.task3.entity.Login;

import java.util.List;

public interface CsvParser {

    List<CsvPosting> parseCsvPostings(List<String> postings);

    List<Login> parseLogins(List<String> logins);
}
