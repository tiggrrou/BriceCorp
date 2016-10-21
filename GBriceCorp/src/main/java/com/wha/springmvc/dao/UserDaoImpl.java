package com.wha.springmvc.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Administrateur;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Justificatif;
import com.wha.springmvc.model.Notification;
import com.wha.springmvc.model.TypeUtilisateur;
import com.wha.springmvc.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	private static int NbUser = 0; 
	
	@Override
	public void addConseillerToAdmin(Conseiller conseiller){
		conseiller.setTypeUser(TypeUtilisateur.Conseiller.getType());	
		
		Set<Client> newClients = new HashSet<Client>();
		conseiller.setClients(newClients);

		Administrateur admin = (Administrateur) getEntityManager().createQuery("SELECT a FROM Administrateur a").getSingleResult();
		admin.getConseillers().add(conseiller);
		
	}

	@Override
	public void deleteConseiller(long idCons) {
		Conseiller conseiller = findConsById(idCons);
		Administrateur admin = (Administrateur) getEntityManager().createQuery("SELECT a FROM Administrateur a").getSingleResult();
		admin.getConseillers().remove(conseiller);

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
	public Client createClient(long idConseiller, Dem_CreationClient demande_inscription){
		NbUser++;
		Client client = new Client();
		client.setTypeUser(TypeUtilisateur.Client.getType());
		client.setIdentifiant("c"+ NbUser);
		client.setMotDePasse("c");
		/*client.setMotDePasse(Password.nextSessionId());*/
		client.setNom(demande_inscription.getNom());
		client.setPrenom(demande_inscription.getPrenom());		
		client.setAdresse(demande_inscription.getAdresse());
		client.setMail(demande_inscription.getMail());
		client.setTelephone(demande_inscription.getTelephone());
		client.setRevenu(demande_inscription.getRevenu());

		
		
  	  	persist(client);			
		


		
	
  	  	/**
  	  	 * Ajout du compte courant "de base"
  	  	 */
  	  
  	  	
		Compte compte = new Compte();
  	  	compte.setLibelle("Compte courant");
  	  	client.getComptes().add(compte);


  	  	/**
  	  	 * ajout de la notifiaction de creation du compte courant
  	  	 */
	  	
  	  	String message = "Votre Compte Courant est ouvert";
  	  	sendNotificationToAClient(message, client.getId());

  	  	
  	  	

  	  Iterator<Justificatif> iter = demande_inscription.getJustificatifs().iterator();
  	while (iter.hasNext()) {
  	  Justificatif justificatif = iter.next();
  	  Justificatif newjustificatif = new Justificatif();
  	  newjustificatif.setDate(justificatif.getDate());
  	  newjustificatif.setType(justificatif.getType());

  	  String url = justificatif.getUrl();
	  String replacedUrl = url.replaceAll("demandes", "clients");
	  newjustificatif.setUrl(replacedUrl);
	  client.getJustificatifs().add(newjustificatif);

//Deplacement des justificatifs du dossier demandes vers le dossier clients
//	   try {
//		Files.move(new File(url).toPath(), new File(replacedUrl).toPath(), StandardCopyOption.REPLACE_EXISTING);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}


  	}
  	
	  demande_inscription.getJustificatifs().removeAll(demande_inscription.getJustificatifs());	
  	 
 		    
 		    
  	  	

		
  	  	/**
  	  	 * Ajout du conseiller au client		
  	  	 */
		Conseiller conseiller = findConsById(idConseiller);
		client.setConseiller(conseiller);
	
		
		/**
		 * retrait de la demande d'inscription de la liste des demandes du conseiller		
		 */
		conseiller.getDemandes().remove(demande_inscription);

   	  	return client;
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
		List<Client> clients = getEntityManager().createQuery("SELECT c.Client FROM Conseiller c WHERE c.id = :consID")
					.setParameter("consID", consID).getResultList();
		return clients;
	}

	
	@Override
	public Client findCliById(long idcli) {
		try {
			Client client = (Client) getEntityManager().createQuery("SELECT c FROM Client c WHERE c.id = :id", Client.class)
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
		try {

			User user = (User) getEntityManager()
					.createQuery("SELECT u FROM User u WHERE u.identifiant = :login AND u.motDePasse = :mdp")
					.setParameter("login", login).setParameter("mdp", mdp).getSingleResult();
			return user;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public void createAdmin(Administrateur admin) {	
		
			persist(admin);
			
		}




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
			return user;
		} catch (NoResultException ex) {
			return null;
		}
	}
	


	

	@Override
	public void sendNotificationToAClient(String message, long clientID) {
		Notification myNotif = new Notification();
		myNotif.setMessage(message);
		
		Client myClient = findCliById(clientID);
		myClient.getNotifications().add(myNotif);

	}

}
