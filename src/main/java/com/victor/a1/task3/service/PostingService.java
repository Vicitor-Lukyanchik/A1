package com.victor.a1.task3.service;

import com.victor.a1.task3.domain.CsvPosting;
import com.victor.a1.task3.entity.Posting;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PostingService {

    void createAll (List<Posting> postings);

    List<CsvPosting> findAuthorizedCsvPostingsByDates(String startDateStr, String endDateStr, Boolean isAuthorized);

    List<Posting> findAll();
}
