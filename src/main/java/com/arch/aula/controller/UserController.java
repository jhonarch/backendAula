package com.arch.aula.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arch.aula.model.User;
import com.arch.aula.service.UserService;
import com.arch.aula.util.RestResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {
	
	@Autowired
	protected UserService userService;
	protected ObjectMapper mapper;
	
	@RequestMapping(name= "/deleteUser", method = RequestMethod.POST )
	public void deleteUser(@RequestBody String userJson) throws Exception {
		this.mapper = new ObjectMapper();
		
		User user = this.mapper.readValue(userJson, User.class);
		
		if(user.getId() == null) {
			throw new Exception("El id es nullo");
		}
		
		this.userService.deleteUser(user.getId());	
		 
	}
	
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
//	public QueryResult getUser() {
	public List<User> getUsers(){
		
		return this.userService.findAll();
	}	
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public RestResponse saveOrUpdate(@RequestBody String userJson)
			throws JsonParseException, JsonMappingException, IOException{
		
		this.mapper = new ObjectMapper();
		User user = this.mapper.readValue(userJson, User.class);
		
		if(!this.validateUser(user)){
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(),
					"Los campos obligatorios no están diligenciados.");
		}
		
		this.userService.save(user);
		
		return new RestResponse(HttpStatus.OK.value(), 
				"Operación éxitosa");
	}
	
	private boolean validateUser(User user) {
		boolean isValid = true;
		
		if (StringUtils.isBlank(user.getFirstName())) {
			isValid = false;
		}
		
		if (StringUtils.isBlank(user.getFirstSurname())) {
			isValid = false;
		}
		
		if (StringUtils.isBlank(user.getAddress())) {
			isValid = false;
		}
		
		return isValid;
	}
	
}
