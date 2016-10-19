package com.wha.springmvc.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wha.springmvc.dao.UserDao;
import com.wha.springmvc.model.Administrateur;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Notification;
import com.wha.springmvc.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static final AtomicLong counter = new AtomicLong();

	@Autowired
	private UserDao dao;

	/**
	 * renvoi l'utilisateur qu possede le login et le MDP
	 * 
	 * @param user
	 * @param mdp
	 * @return User
	 */

	public User connexion(String login, String mdp) {
		return dao.connexion(login, mdp);
	}

	public boolean isUserExist(User user) {
		return false;
	}

	@Override
	public void addConseillerToAdmin(Conseiller conseiller) {

		dao.addConseillerToAdmin(conseiller);
	}

	@Override
	public Conseiller findConsById(long idConseiller) {
		return dao.findConsById(idConseiller);
	}

	@Override
	public void updateConseiller(Conseiller conseiller) {
		dao.updateConseiller(conseiller);
	}

	@Override
	public void deleteClient(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateclient(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Client> findAllClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client findCliById(long idcli) {
		return dao.findCliById(idcli);
	}

	@Override
	public void deleteConseiller(long idCons) {
		dao.deleteConseiller(idCons);

	}

	@Override
	public List<Conseiller> findAllConseillers() {
		return dao.findAllConseillers();
	}

	@Override
	public List<Client> listeDeClientDuConseiller(long idConseiller) {
		return dao.findClientsFromConsID(idConseiller);
	}

	@Override
	public void createAdmin(Administrateur admin) {
		dao.createAdmin(admin);
	}



	@Override
	public Client createClient(long idConseiller, Dem_CreationClient demande_inscription) {
		Client client = dao.createClient(idConseiller, demande_inscription);
		return client;
	}

	@Override
	public void addcompte(Compte compte, long client_id) {
		dao.addcompte(compte, client_id);
	}

	@Override
	public User refresh(long idUser) {

		return dao.refresh(idUser);
	}

	@Override
	public void sendNotificationToAClient(String message, long clientID) {
		dao.sendNotificationToAClient(message, clientID);
		
	}

	@Override
	public void sendMessage(String subject, String text, String destinataire, String copyDest) {
		dao.sendMessage(subject, text, destinataire, copyDest);
		
	}

}
