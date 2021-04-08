package com.crashcourse.demo.service.impl;

import com.crashcourse.demo.io.entity.UserEntity;
import com.crashcourse.demo.repository.UserRepository;
import com.crashcourse.demo.service.UserService;
import com.crashcourse.demo.shared.Utils;
import com.crashcourse.demo.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public UserDto createUser(UserDto user) {


        if (userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record already exists");


        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserId = utils.generateKey(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword("testEncryptedPassword");

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }
}