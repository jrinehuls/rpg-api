package com.jrinehuls.rpgapi.service.impl;

import com.jrinehuls.rpgapi.dto.user.UserDto;
import com.jrinehuls.rpgapi.entity.Monster;
import com.jrinehuls.rpgapi.entity.User;
import com.jrinehuls.rpgapi.exception.conflict.SpellConflictException;
import com.jrinehuls.rpgapi.exception.conflict.UserConflictException;
import com.jrinehuls.rpgapi.exception.notfound.UserNotFoundException;
import com.jrinehuls.rpgapi.repository.UserRepository;
import com.jrinehuls.rpgapi.service.UserService;
import com.jrinehuls.rpgapi.util.ExceptionParser;
import com.jrinehuls.rpgapi.util.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class UserServiceImpl implements UserService {

    // TODO: Handle unique constraint on username. Probably DataIntegrityViolationException.

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found"));
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userMapper.mapDtoToUser(userDto);
        try {
            User savedUser = userRepository.save(user); // DIVE thrown if username taken
            return userMapper.mapUserToDto(savedUser);
        } catch (DataIntegrityViolationException e) {
            String field = ExceptionParser.getUniqueConstraintField(e, "Users");
            throw new UserConflictException(field);
        }

    }

}
