package org.example.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class UserRequestDto {
    private Long id;
    private String name;
    private String surname;
    private LocalDate birthday;
}
