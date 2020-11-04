package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
public class DemoController {

	@Autowired
	UserRepository userDao;

	@GetMapping(value = "/")
	public String home() {
		return "Hello World";

	}

	@GetMapping(value = "/names")
	public List<String> names() {

		List<String> names = new ArrayList<>();

		names.add("Nairobi");
		names.add("Professor");
		names.add("Rio");
		names.add("Tokyo");

		return names;

	}

	@GetMapping(value = "/user/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUsers() {
		
		List<User> allUsers = userDao.findAll();
		
		if(allUsers.isEmpty()) 
			return new ResponseEntity<String>("There is no content in the database.", HttpStatus.OK);
		
		//allUsers.forEach(user -> System.out.println(user));
		
		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
	}
		
	@PostMapping(value = "/user/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createNewUser(@RequestBody User newUser) {
		
		userDao.save(newUser);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/user/{userName}")
	
	public ResponseEntity<String> getUserByUserName(@PathVariable String userName) {
		
		User user = userDao.findByUserName(userName);
		
		if(user == null) {
			return new ResponseEntity<String>("There is no user found in database with this userName ", HttpStatus.OK);
		}
		
		String message = "User with first name = " + user.firstName + " and last name = " +user.lastName + " is found !!!";
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
		
	}
	
	
	@DeleteMapping(value = "/user/delete/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<?> deleteByUserName(@PathVariable String username) {
		
		User user = userDao.findByUserName(username);
		
	 userDao.delete(user);
		
		return new ResponseEntity<>(HttpStatus.OK );
		
	}
	
	@PutMapping(value = "/user/update/{username}")
	
	public ResponseEntity<?> updateUser(@RequestBody User newData, @PathVariable String username) {
		
		User currentUser = userDao.findByUserName(username);
		
		if(currentUser == null) {
			
			return new ResponseEntity<String>("no user found in database with id : " +username, HttpStatus.OK);
		}
		
		
		userDao.save(newData);
		
		return new ResponseEntity<User>(currentUser, HttpStatus.OK); 
		
	}
	

}
