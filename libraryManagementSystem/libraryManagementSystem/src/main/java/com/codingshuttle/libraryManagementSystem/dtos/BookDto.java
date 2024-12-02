package com.codingshuttle.libraryManagementSystem.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookDto {
    private Long id;
    private String isbn;
    private String title;
    private LocalDate publishedOn;
    private BigDecimal price;
    private Integer noOfPages;
}
