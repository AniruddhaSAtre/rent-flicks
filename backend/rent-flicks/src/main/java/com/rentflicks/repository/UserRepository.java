package com.rentflicks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentflicks.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);

}
