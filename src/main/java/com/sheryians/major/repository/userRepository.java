package com.sheryians.major.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheryians.major.model.User;

public interface userRepository extends JpaRepository<User, Integer>{

	Optional<User> findUserByEmail(String email);
}
