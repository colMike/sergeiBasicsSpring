package com.crashcourse.demo.service;


import com.crashcourse.demo.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDto> getAllUsers();

    UserDto getUser(String email);

    UserDto getUserByUserId(String id);

    UserDto createUser(UserDto user);

    UserDto updateUser(String id, UserDto userDto);

    void deleteUser(String userId);
}
