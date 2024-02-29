package org.example.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.controller.error.OpenApi500ErrorResponse;
import org.example.dto.SubscriptionRequestDto;
import org.example.dto.SubscriptionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@OpenApi500ErrorResponse
public interface SubscriptionController {

    @Operation(summary = "Create a new subscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subscription created successfully"),
    })
    @PostMapping
    ResponseEntity<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto);

    @Operation(summary = "Update an existing subscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription updated successfully"),
            @ApiResponse(responseCode = "404", description = "Subscription not found")
    })
    @PutMapping
    ResponseEntity<SubscriptionResponseDto> updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto);

    @Operation(summary = "Delete a subscription by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Subscription deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Subscription not found")
    })
    @DeleteMapping("/{subscriptionId}")
    ResponseEntity<Void> deleteSubscription(@PathVariable @Parameter(description = "ID of the subscription to be deleted") Long subscriptionId);

    @Operation(summary = "Get a subscription by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Subscription not found")
    })
    @GetMapping("/{subscriptionId}")
    ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable @Parameter(description = "ID of the subscription to be retrieved") Long subscriptionId);

    @Operation(summary = "Get all subscriptions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of subscriptions retrieved successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = SubscriptionResponseDto.class))))
    })
    @GetMapping
    ResponseEntity<List<SubscriptionResponseDto>> getAllSubscription();
}
