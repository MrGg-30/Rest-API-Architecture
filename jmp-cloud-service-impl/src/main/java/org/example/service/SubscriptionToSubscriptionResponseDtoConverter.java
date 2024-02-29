package org.example.service;

import org.example.dto.Subscription;
import org.example.dto.SubscriptionResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class SubscriptionToSubscriptionResponseDtoConverter implements Converter<Subscription, SubscriptionResponseDto> {
    @Override
    public SubscriptionResponseDto convert(Subscription source) {
        return new SubscriptionResponseDto()
                .setId(source.getId())
                .setStartDate(source.getStartDate())
                .setUserId(source.getUser().getId());
    }
}