package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.api.SubscriptionService;
import org.example.dto.SubscriptionRequestDto;
import org.example.dto.SubscriptionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Subscription Controller",
        description = "Endpoints to query and manipulate subscriptions")
@RequestMapping("/api/subscriptions")
public class SubscriptionControllerImpl {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionResponseDto createdSubscription = subscriptionService.createSubscription(subscriptionRequestDto);
        URI location = URI.create("/api/users/" + createdSubscription.getId());
        return ResponseEntity.created(location).body(createdSubscription);
    }

    @PutMapping
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionResponseDto updatedSubscription = subscriptionService.updateSubscription(subscriptionRequestDto);
        return ResponseEntity.ok(updatedSubscription);
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{subscriptionId}")
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable Long subscriptionId) {
        SubscriptionResponseDto subscriptionResponseDto = subscriptionService.getSubscription(subscriptionId);
        return ResponseEntity.ok(subscriptionResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscription() {
        List<SubscriptionResponseDto> allSubscriptions = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(allSubscriptions);
    }
}