package com.shippel.app.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shippel.app.requests.UserRequest;
import com.shippel.app.responses.UserResponse;
import com.shippel.app.services.UserService;
import com.shippel.app.shareds.dto.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping
	public String getUser() {
		return "get user was called";
	}

	@PostMapping
	public UserResponse createUser(@RequestBody UserRequest userRequest) {

		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(userRequest, userDto);
		
		UserDto createUser = userService.createUser(userDto);
		
		UserResponse userResponse = new UserResponse();
		
		BeanUtils.copyProperties(createUser, userResponse);
		
		return userResponse;
	}

	@DeleteMapping
	public String deleteUser() {
		return "delete user was called";
	}

	@PutMapping
	public String updateUser() {
		return "update user was called";
	}
}
