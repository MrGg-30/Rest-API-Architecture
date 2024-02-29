package org.example.service;

import org.example.dto.Subscription;
import org.example.dto.SubscriptionRequestDto;
import org.example.dto.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class SubscriptionRequestDtoToSubscriptionConverter implements Converter<SubscriptionRequestDto, Subscription> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Subscription convert(SubscriptionRequestDto source) {
        Optional<User> optionalUser = userRepository.findById(source.getUserId());

        if (optionalUser.isPresent()) {
            return new Subscription()
                    .setId(source.getId())
                    .setStartDate(LocalDate.now())
                    .setUser(optionalUser.get());
        } else {
            throw new NoSuchElementException("User not found with id: " + source.getUserId());
        }
    }
}