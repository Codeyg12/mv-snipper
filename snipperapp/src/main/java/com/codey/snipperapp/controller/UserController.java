package com.codey.snipperapp.controller;

import com.codey.snipperapp.entity.User;
import com.codey.snipperapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User newUser = userService.createUser(user);
    return ResponseEntity.ok(newUser);
  }
}
