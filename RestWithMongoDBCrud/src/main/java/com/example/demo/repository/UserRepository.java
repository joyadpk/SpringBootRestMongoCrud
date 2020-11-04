package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.User;

public interface UserRepository extends MongoRepository<User, Integer> {
	
	User findByUserName(String userName);
	
	List<User> deleteByUserName(String userName);
	
	int removeByUserName(String userName);

}
