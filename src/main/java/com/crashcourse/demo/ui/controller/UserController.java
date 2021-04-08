package com.crashcourse.demo.ui.controller;

import com.crashcourse.demo.ui.model.request.UserDetailsRequestModel;
import com.crashcourse.demo.ui.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

  Map<String, UserRest> users;

  @GetMapping
  public String getUsers(
      @RequestParam(value = "page", defaultValue = "1") int page,
      @RequestParam(value = "limit", defaultValue = "25") int limit) {
    return "get users was these query string request params: " + page + ": " + limit;
  }

  @GetMapping(
      path = "/{userId}",
      produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

    if (users.containsKey(userId)) {
      return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping(
      consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<UserRest> createUser(
      @Valid @RequestBody UserDetailsRequestModel userDetails) {

    UserRest returnValue = new UserRest();
    returnValue.setFirstName(userDetails.getFirstName());
    returnValue.setLastName(userDetails.getLastName());
    returnValue.setEmail(userDetails.getEmail());

    String userId = UUID.randomUUID().toString();
    returnValue.setUserId(userId);

    if (users == null) users = new HashMap<>();
    users.put(userId, returnValue);

    return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<UserRest> updateUser(
      @PathVariable String userId, @RequestBody() UserDetailsRequestModel updatedUser) {

    UserRest user = users.get(userId);

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
