package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.api.UserService;
import org.example.dto.UserRequestDto;
import org.example.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@RestController
@AllArgsConstructor
@Tag(name = "User Controller",
        description = "Endpoints to query and manipulate users")
@RequestMapping("/api/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<EntityModel<UserResponseDto>> createUser(@RequestBody UserRequestDto user) {
        UserResponseDto createdUser = userService.createUser(user);
        EntityModel<UserResponseDto> entityModel = EntityModel.of(createdUser);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getUser(createdUser.getId())).withSelfRel();
        entityModel.add(selfLink);
        return ResponseEntity.created(URI.create(selfLink.getHref())).body(entityModel);
    }

    @PutMapping
    public ResponseEntity<EntityModel<UserResponseDto>> updateUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.updateUser(userRequestDto);
        EntityModel<UserResponseDto> entityModel = EntityModel.of(userResponseDto);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getUser(userResponseDto.getId())).withSelfRel();
        entityModel.add(selfLink);
        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<EntityModel<UserResponseDto>> getUser(@PathVariable Long userId) {
        UserResponseDto userResponseDto = userService.getUser(userId);
        EntityModel<UserResponseDto> entityModel = EntityModel.of(userResponseDto);
        Link allUsersLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getAllUser()).withRel("all-users");
        entityModel.add(allUsersLink);
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<UserResponseDto>>> getAllUser() {
        List<EntityModel<UserResponseDto>> allUsers = userService.getAllUsers().stream()
                .map(userResponseDto -> EntityModel.of(userResponseDto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUser(userResponseDto.getId())).withSelfRel()))
                .collect(Collectors.toList());

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getAllUser()).withSelfRel();
        CollectionModel<EntityModel<UserResponseDto>> collectionModel = CollectionModel.of(allUsers, selfLink);
        return ResponseEntity.ok(collectionModel);
    }
}
