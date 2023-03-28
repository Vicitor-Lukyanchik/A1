package com.victor.a1.task3.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CsvPosting {

    private String matDoc;

    private LocalDate docDate;

    private LocalDate postingDate;

    private String userName;

    private String material;

    private Long quantity;

    private String bun;

    private BigDecimal amount;

    private String currency;

    private Integer item;

    private Boolean isAuthorized;
}
