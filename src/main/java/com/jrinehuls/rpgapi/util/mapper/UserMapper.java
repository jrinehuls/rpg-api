package com.jrinehuls.rpgapi.util.mapper;

import com.jrinehuls.rpgapi.dto.user.UserDto;
import com.jrinehuls.rpgapi.entity.User;

public class UserMapper {

    public User mapDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto mapUserToDto(User user) {
        return new UserDto(user.getUsername(), user.getPassword());
    }

}
