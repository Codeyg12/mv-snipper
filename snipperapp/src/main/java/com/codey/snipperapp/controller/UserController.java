package com.codey.snipperapp.controller;

import com.codey.snipperapp.entity.User;
import com.codey.snipperapp.service.UserService;
import com.codey.snipperapp.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final JwtUtil jwtUtil;

  public UserController(UserService userService, JwtUtil jwtUtil) {
    this.userService = userService;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    try {
      User registeredUser = userService.createUser(user);
      String token = jwtUtil.generateToken(registeredUser.getEmail());

      AuthResponse response = new AuthResponse(token);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
    try {
      User authenticatedUser = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
      String token = jwtUtil.generateToken(authenticatedUser.getEmail());

      AuthResponse response = new AuthResponse(token);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}

class LoginRequest {
  private String email;
  private String password;

  // Getters and setters
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

class AuthResponse {
  private String token;

  public AuthResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}