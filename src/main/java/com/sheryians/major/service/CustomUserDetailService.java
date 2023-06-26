package com.sheryians.major.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.CustomUserDetail;
import com.sheryians.major.model.User;
import com.sheryians.major.repository.userRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	userRepository userrepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userrepository.findUserByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		
		return user.map(CustomUserDetail::new).get();
	}

}
