package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.User;



public interface UserService {
	
	User findById(long id);
	
	List<User> trouveParNom(String nom);
	
	List<User> trouveParPrenom(String prenom);

	List<User> trouveParNomEtPrenom(String nom, String prenom);
	
	User findByLogin(String login);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);
	
	String connexion(String login, String mdp);
	
	boolean checkPassword(User user, String mdp);
	
}
