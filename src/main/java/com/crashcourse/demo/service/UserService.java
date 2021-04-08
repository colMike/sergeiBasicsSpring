package com.crashcourse.demo.service;


import com.crashcourse.demo.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto user);

}
