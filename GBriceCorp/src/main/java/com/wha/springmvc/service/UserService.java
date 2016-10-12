package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.User;



public interface UserService {
	
	User findById(long id);
	
	User trouveParNom(String nom);
	
	User trouveParPrenom(String prenom);

	User trouveParMail(String mail);
	
	List<User> getUser_Cons(long id); 

	List<User> trouveParType(int typeUser);
	
	User findByLogin(String login);
	

	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);
	
	String connexion(String login, String mdp);
	
	boolean checkPassword(User user, String mdp);
	
	public void saveConseiller(User user);

	List<User> getUser_Admin_Cons();

	Conseiller findConsById(long idCons);
	
}
