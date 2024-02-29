package org.example.service;

import org.example.dto.User;
import org.example.dto.UserRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRequestDtoToUserConverter implements Converter<UserRequestDto, User> {
    @Override
    public User convert(UserRequestDto source) {
        return new User()
                .setId(source.getId())
                .setName(source.getName())
                .setBirthday(source.getBirthday())
                .setSurname(source.getSurname());
    }
}