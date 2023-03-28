package com.victor.a1.task3.converter;

import com.victor.a1.task3.domain.CsvPosting;
import com.victor.a1.task3.entity.Posting;
import com.victor.a1.task3.entity.Product;

import java.util.List;

public interface CsvPostingsConverter {

    List<Posting> convertPostings(List<CsvPosting> csvPostings, List<Product> products);

    List<Product> convertProducts(List<CsvPosting> postings);

    List<CsvPosting> convertCsvPostings(List<Posting> postings);
}
