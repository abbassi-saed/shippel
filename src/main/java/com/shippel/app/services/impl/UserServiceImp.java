package com.shippel.app.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shippel.app.entities.UserEntity;
import com.shippel.app.repositories.UserRepository;
import com.shippel.app.services.UserService;
import com.shippel.app.shareds.Utils;
import com.shippel.app.shareds.dto.UserDto;


@Service
public class UserServiceImp implements UserService {

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	Utils util;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		
		UserEntity userEntity = new UserEntity();
		
		UserEntity checkUser = userRepository.findByEmail(user.getEmail());
		
		if(checkUser != null) throw new RuntimeException("user already exists !");
		
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setUserId(util.generateStringId(32));
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				
		UserEntity newUser = userRepository.save(userEntity);
		
		UserDto userDto	= new UserDto();
		
		BeanUtils.copyProperties(newUser, userDto);
				
		return userDto;
	}

}
