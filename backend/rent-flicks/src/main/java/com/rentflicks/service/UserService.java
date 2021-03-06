package com.rentflicks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentflicks.model.User;
import com.rentflicks.repository.UserRepository;

@Service
public class UserService {

	UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public User createUser(User user) {
		user = userRepository.save(user);
		return user;
	}

	public User signIn(User user) {
		return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
	}
	
	public User findOne(Integer userId){
		User user = userRepository.findOne(userId);
		return user;
	}

}
