package com.jrinehuls.rpgapi.service;

import com.jrinehuls.rpgapi.dto.user.UserDto;
import com.jrinehuls.rpgapi.entity.User;

public interface UserService {

    User getUser(String username);

    UserDto registerUser(UserDto userDto);


}
