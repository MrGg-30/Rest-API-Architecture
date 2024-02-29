package org.example.service;

import org.example.dto.User;
import org.example.dto.UserResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserResponseDtoConverter implements Converter<User, UserResponseDto> {

    @Override
    public UserResponseDto convert(User source) {
        return new UserResponseDto()
                .setId(source.getId())
                .setBirthday(source.getBirthday())
                .setName(source.getName())
                .setSurname(source.getSurname());
    }
}