package com.victor.a1.task3.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

//I don't know is amount refers to product or to posting, and I add (quantity, amount, item, currency, BUn) in posting
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "postings")
public class Posting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mat_doc")
    private String matDoc;

    @Column(name = "doc_date")
    private LocalDate docDate;

    @Column(name = "posting_date")
    private LocalDate postingDate;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "bun")
    private String bun;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "item")
    private Integer item;

    @Column(name = "is_authorized")
    private Boolean isAuthorized;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
