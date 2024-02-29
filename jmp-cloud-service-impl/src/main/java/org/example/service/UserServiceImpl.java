package org.example.service;

import lombok.AllArgsConstructor;
import org.example.api.UserService;
import org.example.dto.User;
import org.example.dto.UserRequestDto;
import org.example.dto.UserResponseDto;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserToUserResponseDtoConverter userToUserResponseDtoConverter;
    private final UserRequestDtoToUserConverter userRequestDtoToUserConverter;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequest) {
        User user = userRequestDtoToUserConverter.convert(userRequest);
        return userToUserResponseDtoConverter.convert(userRepository.save(user));
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        User user = userRepository.findById(userRequestDto.getId()).orElse(null);
        if (user == null) {
            User newUser = userRequestDtoToUserConverter.convert(userRequestDto);
            user = userRepository.save(newUser);
        } else {
            user.setName(userRequestDto.getName());
            user.setSurname(userRequestDto.getSurname());
            user.setBirthday(userRequestDto.getBirthday());
            user = userRepository.save(user);
        }
        return userToUserResponseDtoConverter.convert(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponseDto getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return userToUserResponseDtoConverter.convert(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(x -> userToUserResponseDtoConverter.convert(x))
                .collect(Collectors.toList());
    }
}