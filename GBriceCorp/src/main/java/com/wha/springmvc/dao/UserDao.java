package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.User;


public interface UserDao {
	
	User findById(int id);
	
	User findByName(String name);
	
	void save(User user);
	
	void deleteUserById(int id);
	
	List<User> findAllUsers();
	
	void deleteAllUsers();
	
}
