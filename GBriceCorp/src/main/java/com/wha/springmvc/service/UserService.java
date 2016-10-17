package com.wha.springmvc.service;

import java.util.List;

import com.wha.springmvc.model.Administrateur;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.User;



public interface UserService {
	
	public boolean isUserExist(User user);
	
	public User connexion(String login, String mdp);
	
	public User refresh(long idUser);
	
	

	
	public void deleteClient(long id);

	public void updateclient(Client client);

	List<Client> findAllClients();

	Client findCliById(long idcli);
	

	
	
	public void addConseillerToAdmin(Conseiller conseiller);

	public void deleteConseiller(long id);

	public void updateConseiller(Conseiller conseiller);
	
	List<Conseiller> findAllConseillers();

	public Conseiller findConsById(long idCons);
	

	
	
	List<Client> listeDeClientDuConseiller(long idConseiller); 


	
	
	public void createAdmin(Administrateur admin);
	
	public void createClient(long idConseiller, Dem_CreationClient demande_inscription);

	

	public void addcompte(Compte compte, long client_id);

	
}
