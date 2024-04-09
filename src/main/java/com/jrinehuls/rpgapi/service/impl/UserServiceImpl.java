package com.jrinehuls.rpgapi.service.impl;

import com.jrinehuls.rpgapi.dto.user.UserDto;
import com.jrinehuls.rpgapi.entity.User;
import com.jrinehuls.rpgapi.repository.UserRepository;
import com.jrinehuls.rpgapi.service.UserService;
import com.jrinehuls.rpgapi.util.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class UserServiceImpl implements UserService {

    // TODO: Handle unique constraint on username. Probably DataIntegrityViolationException.

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = userMapper.mapDtoToUser(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.mapUserToDto(savedUser);
    }

}
