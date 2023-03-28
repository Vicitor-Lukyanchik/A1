package com.victor.a1.task3.service.impl;

import com.victor.a1.task3.converter.CsvPostingsConverter;
import com.victor.a1.task3.domain.CsvPosting;
import com.victor.a1.task3.entity.Login;
import com.victor.a1.task3.entity.Posting;
import com.victor.a1.task3.entity.Product;
import com.victor.a1.task3.exception.ServiceException;
import com.victor.a1.task3.parser.CsvParser;
import com.victor.a1.task3.parser.DateParser;
import com.victor.a1.task3.reader.CsvFileReader;
import com.victor.a1.task3.repository.PostingRepository;
import com.victor.a1.task3.service.LoginService;
import com.victor.a1.task3.service.PostingService;
import com.victor.a1.task3.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {

    private static final String POSTINGS_CSV_PATH = "csv/postings.csv";
    private static final String LOGINS_CSV_PATH = "csv/logins.csv";

    private final PostingRepository postingRepository;
    private final ProductService productService;
    private final LoginService loginService;
    private final CsvFileReader csvFileReader;
    private final CsvParser csvParser;
    private final CsvPostingsConverter postingsConverter;
    private final DateParser dateParser;

    @PostConstruct
    private void saveCsvFiles() {
        List<Login> logins = csvParser.parseLogins(csvFileReader.readResourceFileLines(LOGINS_CSV_PATH));
        loginService.createAll(logins);

        List<CsvPosting> csvPostings =
                csvParser.parseCsvPostings(csvFileReader.readResourceFileLines(POSTINGS_CSV_PATH));
        List<Product> products = postingsConverter.convertProducts(csvPostings);
        productService.createAll(products);

        List<Posting> postings = defineIsAuthorizedPostings(
                postingsConverter.convertPostings(csvPostings, productService.findAll()), loginService.findAll());
        createAll(postings);
    }

    private List<Posting> defineIsAuthorizedPostings(List<Posting> postings, List<Login> logins) {
        for (Posting posting : postings) {
            posting.setIsAuthorized(isExistActiveLoginWithUserName(logins, posting.getUserName()));
        }
        return postings;
    }

    private Boolean isExistActiveLoginWithUserName(List<Login> logins, String userName) {
        for (Login login : logins) {
            if (login.getAppAccountName().equals(userName)) {
                return login.getIsActive();
            }
        }
        return false;
    }

    @Override
    public void createAll(List<Posting> postings) {
        postingRepository.saveAll(postings);
    }


    @Override
    public List<CsvPosting> findAuthorizedCsvPostingsByDates(String startDateStr, String endDateStr, Boolean isAuthorized) {
        LocalDate startDate = dateParser.parseDate(startDateStr);
        LocalDate endDate = dateParser.parseDate(endDateStr);
        checkDate(startDate, endDate);

        List<Posting> postings = findAuthorizedPostings(findPostingsByDates(startDate, endDate), isAuthorized);
        return postingsConverter.convertCsvPostings(postings);
    }

    private void checkDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new ServiceException("Start date can't be after than end date");
        }
    }

    private List<Posting> findPostingsByDates(LocalDate startDate, LocalDate endDate) {
        return findAll().stream().filter(posting ->
                        ((posting.getDocDate().isEqual(startDate) || posting.getDocDate().isAfter(startDate)) &&
                                (posting.getDocDate().isBefore(endDate) || posting.getDocDate().isEqual(endDate))))
                .collect(Collectors.toList());
    }

    private List<Posting> findAuthorizedPostings(List<Posting> postings, Boolean isAuthorized) {
        if (isAuthorized) {
            return postings.stream().filter(posting -> posting.getIsAuthorized()).collect(Collectors.toList());
        } else {
            return postings;
        }
    }

    @Override
    public List<Posting> findAll() {
        return postingRepository.findAll();
    }
}
