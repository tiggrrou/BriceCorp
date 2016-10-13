package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;
import com.wha.springmvc.model.User;

public interface UserDao {

	public void createConseiller(Conseiller conseiller);

	public void deleteConseiller(long id);

	public void updateConseiller(Conseiller conseiller);
	
	List<Conseiller> findAllConseillers();

	Conseiller findConsById(long idCons);
	
	
	
	public void createClient(Client client);
	
	public void deleteClient(long id);

	public void updateclient(Client client);

	List<Client> findAllClients();

	Client findCliById(long idcli);
	
}
