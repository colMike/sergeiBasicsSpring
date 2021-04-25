package com.crashcourse.demo.ui.controller;

import com.crashcourse.demo.service.UserService;
import com.crashcourse.demo.shared.dto.UserDto;
import com.crashcourse.demo.ui.model.request.UserDetailsRequestModel;
import com.crashcourse.demo.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

  Map<String, UserRest> users;

  @Autowired UserService userService;

  @GetMapping
  public String getUsers(
      @RequestParam(value = "page", defaultValue = "1") int page,
      @RequestParam(value = "limit", defaultValue = "25") int limit) {

    //    userService.

    return "get users was called with these query string request params: " + page + ": " + limit;
  }

  @GetMapping(
      path = "/{id}",
      produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<UserRest> getUser(@PathVariable String id) {

    UserRest returnValue = new UserRest();

    UserDto userDto = userService.getUserByUserId(id);
    BeanUtils.copyProperties(userDto, returnValue);

    return new ResponseEntity<>(returnValue, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<UserRest> createUser(
      @Valid @RequestBody UserDetailsRequestModel userDetails) {

    UserRest returnValue = new UserRest();

    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(userDetails, userDto);

    UserDto createdUser = userService.createUser(userDto);
    BeanUtils.copyProperties(createdUser, returnValue);

    return new ResponseEntity<>(returnValue, HttpStatus.OK);
  }

  @PutMapping(
      path = "/{id}",
      produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<UserRest> updateUser(
      @PathVariable String id, @RequestBody() UserDetailsRequestModel updatedUser) {

    UserRest user = users.get(id);

    user.setFirstName(updatedUser.getFirstName());
    user.setLastName(updatedUser.getLastName());

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable String userId) {

    users.remove(userId);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
