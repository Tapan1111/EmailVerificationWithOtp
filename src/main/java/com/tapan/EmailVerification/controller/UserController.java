package com.tapan.EmailVerification.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tapan.EmailVerification.requests.RegisterRequest;
import com.tapan.EmailVerification.response.RegisterResponse;
import com.tapan.EmailVerification.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
		RegisterResponse response = userService.register(registerRequest);
		return new ResponseEntity<RegisterResponse>(response, HttpStatus.CREATED);
	}



}
