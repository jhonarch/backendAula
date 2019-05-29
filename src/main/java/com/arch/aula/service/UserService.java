package com.arch.aula.service;

import java.util.List;

import com.arch.aula.model.User;

public interface UserService {

	User save(User user);

	List<User> findAll();

	void deleteUser(Long id);

}
