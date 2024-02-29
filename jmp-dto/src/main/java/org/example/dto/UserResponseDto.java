package org.example.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class UserResponseDto extends RepresentationModel<UserResponseDto> {
    private Long id;
    private String name;
    private String surname;
    private LocalDate birthday;
}