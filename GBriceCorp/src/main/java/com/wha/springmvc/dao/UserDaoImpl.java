package com.wha.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Administrateur;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Demande;
import com.wha.springmvc.model.Notification;
import com.wha.springmvc.model.TypeUtilisateur;
import com.wha.springmvc.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	@Override
	public void addConseillerToAdmin(Conseiller conseiller){
		conseiller.setTypeUser(TypeUtilisateur.Conseiller.getType());	
		
		List<Client> newClients = new ArrayList<Client>();
		conseiller.setClients(newClients);

		List<Demande> listDemandes = new ArrayList<Demande>();
		conseiller.setDemandes(listDemandes);
		
		persist(conseiller);

		
		
		Administrateur admin = (Administrateur) getEntityManager().createQuery("SELECT a FROM Administrateur a WHERE a.id = 1").getSingleResult();
		
		List<Conseiller> listConseillers = admin.getConseillers();
		listConseillers.add(conseiller);
		admin.setConseillers(listConseillers);
		

		
		System.out.println(conseiller);
		
	}

	@Override
	public void deleteConseiller(long idCons) {
		Conseiller conseiller = findConsById(idCons);
		delete(conseiller);
	}

	@Override
	public void updateConseiller(Conseiller conseiller) {
		Conseiller oldcons = findConsById(conseiller.getId());
		oldcons.setNom(conseiller.getNom());
		oldcons.setPrenom(conseiller.getPrenom());
		oldcons.setAdresse(conseiller.getAdresse());
		oldcons.setMail(conseiller.getMail());
		oldcons.setFinContrat(conseiller.getFinContrat());
		oldcons.setTelephone(conseiller.getTelephone());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Conseiller> findAllConseillers() {

		List<Conseiller> conseillers = getEntityManager().createQuery("SELECT c FROM Conseiller c").getResultList();
		return conseillers;
	}

	@Override
	public Conseiller findConsById(long idCons) {
		try {
			Conseiller conseiller = (Conseiller) getEntityManager()
					.createQuery("SELECT c FROM Conseiller c WHERE c.id = :id").setParameter("id", idCons)
					.getSingleResult();
			return conseiller;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public void createClient(long idConseiller, Dem_CreationClient demande_inscription){
		Client client = new Client();
		client.setTypeUser(TypeUtilisateur.Client.getType());
		client.setIdentifiant("c");
		client.setMotDePasse("c");
		client.setNom(demande_inscription.getNom());
		client.setPrenom(demande_inscription.getPrenom());		
		client.setAdresse(demande_inscription.getAdresse());
		client.setMail(demande_inscription.getMail());
		client.setTelephone(demande_inscription.getTelephone());
		client.setRevenu(demande_inscription.getRevenu());
		
		List<Compte> newComptes = new ArrayList<Compte>();
		Compte compte = new Compte();
  	  	compte.setLibelle("Compte courant");
  	    newComptes.add(compte);
  	  	client.setComptes(newComptes);

  	  	List<Notification> newNotification = new ArrayList<Notification>();
  	  	Notification notification = new Notification();
  	  	notification.setMessage("Votre Compte Courant est ouvert");
  	  	newNotification.add(notification);
  	  	client.setNotifications(newNotification);
  	  
  	  	
		persist(client);
		Conseiller conseiller = findConsById(idConseiller);
		client.setConseiller(conseiller);
		
//		Conseiller conseiller = (Conseiller) getEntityManager()
//				.createQuery("SELECT c FROM Conseiller c WHERE c.id = :id").setParameter("id", idConseiller)
//				.getSingleResult();
//		
//		List<Client> listClients = conseiller.getClients();
//		listClients.add(client);
//		conseiller.setClients(listClients);
  	  

  	  	System.out.println(conseiller);
  	 
	}

	@Override
	public void deleteClient(long id) {
		Client client = findCliById(id);
		delete(client);

	}

	@Override
	public void updateclient(Client client) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> findAllClients() {
		List<Client> clients = getEntityManager().createQuery("FROM Client").getResultList();
		return clients;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> findClientsFromConsID(long consID) {
		List<Client> clients = getEntityManager().createQuery("FROM Client C where C.conseillerID = :consID")
					.setParameter("consID", consID).getResultList();
		return clients;
	}

	
	@Override
	public Client findCliById(long idcli) {
		try {
			Client client = (Client) getEntityManager().createQuery("SELECT c FROM Client c WHERE c.id = :id")
					.setParameter("id", idcli).getSingleResult();
			return client;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> listeDeClientDuConseiller(long idConseiller) {
		try {
			List<Client> clients = getEntityManager()
					.createQuery("SELECT c FROM Client c WHERE c.conseillerID = :idConseiller")
					.setParameter("idConseiller", idConseiller).getResultList();
			return clients;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public User connexion(String login, String mdp) {
		System.out.println(login+ " / " + mdp);
		try {
			User user = (User) getEntityManager()
					.createQuery("SELECT u FROM User u WHERE u.identifiant = :login AND u.motDePasse = :mdp")
					.setParameter("login", login).setParameter("mdp", mdp).getSingleResult();
System.out.println(user);
			return user;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public void createAdmin(Administrateur admin) {				
			persist(admin);

			List<Dem_CreationClient> newDemandeCreationClient = new ArrayList<Dem_CreationClient>();
			admin.setDemandeCreationClient(newDemandeCreationClient);
			
			List<Conseiller> newConseiller = new ArrayList<Conseiller>();
			admin.setConseillers(newConseiller);

		}



//
//	@Override
//	public boolean attributionCli2Cons(long idCons, long idCli) {
//		
//		for (Client clientEnAffectation : findAllClients()) {
//			if (clientEnAffectation.getId() == idCli) {
//				clientEnAffectation.setConseillerID(idCons);
//				System.out.println(clientEnAffectation);
//				return true;
//			}
//		}
//		return false;
//	}

	@Override
	public void addcompte(Compte compte, long client_id) {
			Client client = findCliById(client_id);
			client.getComptes().add(compte);

	}

	@Override
	public User refresh(long idUser) {
		try {
			User user = (User) getEntityManager()
					.createQuery("SELECT u FROM User u WHERE u.id = :id").setParameter("id", idUser)
					.getSingleResult();
			System.out.println(user);
			return user;
		} catch (NoResultException ex) {
			return null;
		}
	}

}
