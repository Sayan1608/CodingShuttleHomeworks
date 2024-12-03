package com.codingshuttle.libraryManagementSystem.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthorDto {
    private Long id;

    @NotBlank(message = "author name cannot be blank")
    private String name;
}
