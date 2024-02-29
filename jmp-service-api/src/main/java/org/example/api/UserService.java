package org.example.api;

import org.example.dto.UserRequestDto;
import org.example.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto user);

    UserResponseDto updateUser(UserRequestDto userRequestDto);

    void deleteUser(Long userId);

    UserResponseDto getUser(Long userId);

    List<UserResponseDto> getAllUsers();
}