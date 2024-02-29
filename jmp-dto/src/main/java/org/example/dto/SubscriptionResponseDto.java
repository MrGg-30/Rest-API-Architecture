package org.example.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class SubscriptionResponseDto {
    private Long id;
    private Long userId;
    private LocalDate startDate;
}