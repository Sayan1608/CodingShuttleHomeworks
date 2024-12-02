package com.codingshuttle.libraryManagementSystem.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthorDto {
    private Long id;
    private String name;
}
