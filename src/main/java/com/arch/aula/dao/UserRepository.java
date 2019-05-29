package com.arch.aula.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arch.aula.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	 @SuppressWarnings("unchecked")
	User save(User user);
	 
//	List<User> findAll();

}
