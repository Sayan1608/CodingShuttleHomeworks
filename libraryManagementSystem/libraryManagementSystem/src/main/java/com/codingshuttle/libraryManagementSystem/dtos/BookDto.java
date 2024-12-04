package com.codingshuttle.libraryManagementSystem.dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookDto {
    private Long id;

    @NotBlank(message = "isbn for book cannot be blank")
    @ISBN(type = ISBN.Type.ANY,message = "please enter a valid ISBN for the book")
    private String isbn;

    @NotBlank(message = "book title cannot be blank")
    private String title;

    @PastOrPresent(message = "book published date has to be in the past or present")
    private LocalDate publishedOn;

    @Positive(message = "book price has to be a positive value")
    @DecimalMin(value = "99.99", message = "book price should be at least 99.99")
    private BigDecimal price;

    @Min(value = 100, message = "No of pages should be at least 100")
    private Integer noOfPages;

}
