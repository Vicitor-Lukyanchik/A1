package com.victor.a1.task3.converter;

import com.victor.a1.task3.domain.CsvPosting;
import com.victor.a1.task3.entity.Posting;
import com.victor.a1.task3.entity.Product;
import com.victor.a1.task3.exception.ConverterException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CsvPostingsConverterImpl implements CsvPostingsConverter {

    @Override
    public List<Posting> convertPostings(List<CsvPosting> csvPostings, List<Product> products) {
        List<Posting> postings = new ArrayList<>();
        for (CsvPosting csvPosting : csvPostings) {
            Product product = findProductByMaterial(products, csvPosting.getMaterial());
            postings.add(convertPosting(csvPosting, product));
        }
        return postings;
    }

    private Posting convertPosting(CsvPosting csvPosting, Product product) {
        return Posting.builder().matDoc(csvPosting.getMatDoc()).item(csvPosting.getItem())
                .docDate(csvPosting.getDocDate()).postingDate(csvPosting.getPostingDate())
                .quantity(csvPosting.getQuantity()).bun(csvPosting.getBun())
                .amount(csvPosting.getAmount()).currency(csvPosting.getCurrency())
                .userName(csvPosting.getUserName()).product(product).build();
    }

    private Product findProductByMaterial(List<Product> products, String material) {
        for (Product product : products) {
            if (product.getMaterial().equals(material)) {
                return product;
            }
        }
        throw new ConverterException("Product hasn't been founded with name : " + material);
    }

    @Override
    public List<Product> convertProducts(List<CsvPosting> csvPostings) {
        Set<Product> products = new HashSet<>();
        for (CsvPosting csvPosting : csvPostings) {
            products.add(convertProduct(csvPosting));
        }
        return new ArrayList<>(products);
    }

    private Product convertProduct(CsvPosting csvPosting) {
        return Product.builder().material(csvPosting.getMaterial())
                .build();
    }

    @Override
    public List<CsvPosting> convertCsvPostings(List<Posting> postings) {
        List<CsvPosting> result = new ArrayList<>();
        for (Posting posting : postings) {
            result.add(convertCsvPosting(posting));
        }
        return result;
    }

    private CsvPosting convertCsvPosting(Posting posting) {
        return CsvPosting.builder().matDoc(posting.getMatDoc()).item(posting.getItem())
                .docDate(posting.getDocDate()).postingDate(posting.getPostingDate())
                .quantity(posting.getQuantity()).bun(posting.getBun()).amount(posting.getAmount())
                .currency(posting.getCurrency()).userName(posting.getUserName())
                .material(posting.getProduct().getMaterial()).isAuthorized(posting.getIsAuthorized()).build();
    }
}
