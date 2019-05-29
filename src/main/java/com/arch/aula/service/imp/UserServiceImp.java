package com.arch.aula.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arch.aula.dao.UserRepository;
import com.arch.aula.model.User;
import com.arch.aula.service.UserService;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	protected UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);;
	}
	
}
