package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection="user")
public class User {
    @Id
	public int userId;
	
	public String userName;
	
	public String password;
	
	public String firstName;
	
	public String lastName;
	
	public String securityQuestion;
	
	public String securityAnswer;
	
}
