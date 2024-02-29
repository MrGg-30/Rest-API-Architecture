package org.example.service;

import lombok.AllArgsConstructor;
import org.example.api.SubscriptionService;
import org.example.dto.Subscription;
import org.example.dto.SubscriptionRequestDto;
import org.example.dto.SubscriptionResponseDto;
import org.example.dto.User;
import org.example.repository.SubscriptionRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionRequestDtoToSubscriptionConverter subscriptionRequestDtoToSubscriptionConverter;
    private final SubscriptionToSubscriptionResponseDtoConverter subscriptionToSubscriptionResponseDtoConverter;

    @Override
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = subscriptionRequestDtoToSubscriptionConverter.convert(subscriptionRequestDto);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return subscriptionToSubscriptionResponseDtoConverter.convert(savedSubscription);
    }

    @Override
    public SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = subscriptionRepository.findById(subscriptionRequestDto.getId()).orElse(null);
        if (subscription == null) {
            Subscription newSubscription = subscriptionRequestDtoToSubscriptionConverter.convert(subscriptionRequestDto);
            subscription = subscriptionRepository.save(newSubscription);
        } else {
            updateExistingSubscription(subscription, subscriptionRequestDto);
        }
        return subscriptionToSubscriptionResponseDtoConverter.convert(subscription);
    }

    private void updateExistingSubscription(Subscription subscription, SubscriptionRequestDto subscriptionRequestDto) {
        Optional<User> optionalUser = userRepository.findById(subscriptionRequestDto.getUserId());
        if (optionalUser.isPresent()) {
            subscription.setStartDate(LocalDate.now());
            subscription.setId(subscriptionRequestDto.getId());
            subscription.setUser(optionalUser.get());
            subscriptionRepository.save(subscription);
        } else {
            throw new NoSuchElementException("User not found with id: " + subscriptionRequestDto.getUserId());
        }
    }

    @Override
    public void deleteSubscription(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }

    @Override
    public SubscriptionResponseDto getSubscription(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new NoSuchElementException("Subscription not found"));
        return subscriptionToSubscriptionResponseDtoConverter.convert(subscription);
    }

    @Override
    public List<SubscriptionResponseDto> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions.stream()
                    .map(subscriptionToSubscriptionResponseDtoConverter::convert)
                .collect(Collectors.toList());
    }
}