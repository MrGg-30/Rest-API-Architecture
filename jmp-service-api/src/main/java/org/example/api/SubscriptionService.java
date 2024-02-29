package org.example.api;

import org.example.dto.SubscriptionRequestDto;
import org.example.dto.SubscriptionResponseDto;

import java.util.List;

public interface SubscriptionService {
    SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto);

    SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto);

    void deleteSubscription(Long subscriptionId);

    SubscriptionResponseDto getSubscription(Long subscriptionId);

    List<SubscriptionResponseDto> getAllSubscriptions();
}
