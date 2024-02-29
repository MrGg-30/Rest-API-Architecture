package org.example.controller;

import org.example.controller.error.OpenApi500ErrorResponse;
import org.example.dto.UserRequestDto;
import org.example.dto.UserResponseDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@OpenApi500ErrorResponse
public interface UserController {

    @Operation(summary = "Create a new user")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @PostMapping
    ResponseEntity<EntityModel<UserResponseDto>> createUser(@RequestBody UserRequestDto user);

    @Operation(summary = "Update an existing user")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @PutMapping
    ResponseEntity<EntityModel<UserResponseDto>> updateUser(@RequestBody UserRequestDto userRequestDto);

    @Operation(summary = "Delete a user by ID")
    @ApiResponse(responseCode = "204", description = "User deleted successfully")
    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable @Parameter(description = "ID of the user to be deleted") Long userId);

    @Operation(summary = "Get a user by ID")
    @ApiResponse(responseCode = "200", description = "User retrieved successfully")
    @GetMapping("/{userId}")
    ResponseEntity<EntityModel<UserResponseDto>> getUser(@PathVariable @Parameter(description = "ID of the user to be retrieved") Long userId);

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "List of users retrieved successfully",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class))))
    @GetMapping
    ResponseEntity<CollectionModel<EntityModel<UserResponseDto>>> getAllUser();
}
