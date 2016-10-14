
package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Administrateur;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.User;

public interface UserDao {

	public User connexion(String login, String mdp);
	
	
	public void createClient(long id_conseiller, Dem_CreationClient demande_inscription);
	
	public void deleteClient(long idClient);

	public void updateclient(Client client);

	List<Client> findAllClients();

	Client findCliById(long idClient);
	
	public void addcompte(Compte compte, long client_id);
	
	List<Client> findClientsFromConsID (long consID);
	
	public void createConseiller(Conseiller conseiller);

	public void deleteConseiller(long idConseiller);

	public void updateConseiller(Conseiller conseiller);
	
	List<Conseiller> findAllConseillers();

	Conseiller findConsById(long idConseiller);
	
	List<Client> listeDeClientDuConseiller(long idConseiller); 
	
	public boolean attributionCli2Cons(long idCons, long idCli);
	
	
	public void createAdmin(Administrateur admin);

