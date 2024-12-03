package com.codingshuttle.libraryManagementSystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String isbn;

    @Column(nullable = false,unique = true)
    private String title;

    private LocalDate publishedOn;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer noOfPages;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_author_id",referencedColumnName = "id")
    private AuthorEntity bookAuthor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(isbn, that.isbn) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title);
    }


}
