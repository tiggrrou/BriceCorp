package com.wha.springmvc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
import com.wha.springmvc.model.Demande;
import com.wha.springmvc.model.Notification;
import com.wha.springmvc.model.TypeUtilisateur;
import com.wha.springmvc.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	private static int NbUser = 0; 
	
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
	public Client createClient(long idConseiller, Dem_CreationClient demande_inscription){
		NbUser++;
		Client client = new Client();
		client.setTypeUser(TypeUtilisateur.Client.getType());
		client.setIdentifiant("c"+ NbUser);
		client.setMotDePasse("c");
		client.setNom(demande_inscription.getNom());
		client.setPrenom(demande_inscription.getPrenom());		
		client.setAdresse(demande_inscription.getAdresse());
		client.setMail(demande_inscription.getMail());
		client.setTelephone(demande_inscription.getTelephone());
		client.setRevenu(demande_inscription.getRevenu());

		
		
		client.setJustificatifs(demande_inscription.getJustificatifs());
		


		
	
/**
 * Ajout du compte courant "de base"
 */
		List<Compte> newComptes = new ArrayList<Compte>();
		Compte compte = new Compte();
  	  	compte.setLibelle("Compte courant");
  	    newComptes.add(compte);
  	  	client.setComptes(newComptes);

  	  	/**
  	  	 * ajout de la notifiaction de creation du compte courant
  	  	 */
  	  	persist(client);
  	  	
  	  	String message = "Votre Compte Courant est ouvert";
  	  	sendNotificationToAClient(message, client.getId());
		
/**
 * Ajout du conseiller au client		
 */
		Conseiller conseiller = findConsById(idConseiller);
		client.setConseiller(conseiller);
	
		
/**
 * retrait de la demande d'inscription de la liste des demandes du conseiller		
 */
		List<Demande> listDemandes = conseiller.getDemandes();
		List<Demande> newListDemandes = new ArrayList<Demande>();
		    for (Demande demande : listDemandes ){
				    if (demande_inscription.getID() != demande.getID()) {
				    	newListDemandes.add(demande);
				    } 
			    }
		    conseiller.setDemandes(newListDemandes);
		    
		    
   	  	System.out.println(conseiller);
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
	

public void sendMessage(String subject, String text, String destinataire, String copyDest) {
	
		String SMTP_HOST1 = "smtp.gmail.com";
		String LOGIN_SMTP1 = "GB.BriceCorp@gmail.com";
		String IMAP_ACCOUNT1 = "GB.BriceCorp@gmail.com";
		String PASSWORD_SMTP1 = "BriceCorp!";
	
	
		    // 1 -> Création de la session
		    Properties properties = new Properties();
		    properties.setProperty("mail.transport.protocol", "smtp");
		    properties.setProperty("mail.smtp.ssl.enable", "true");
		    properties.setProperty("mail.smtp.host", SMTP_HOST1);
		    properties.setProperty("mail.smtp.user", LOGIN_SMTP1);
		    properties.setProperty("mail.from", IMAP_ACCOUNT1);
		    Session session = Session.getInstance(properties);

		 // 2 -> Création du message
		    MimeMessage message = new MimeMessage(session);
		    try {
		        message.setText(text);
		        message.setContent(text, "text/html");
		        message.setSubject(subject);
		        message.addRecipients(Message.RecipientType.TO, destinataire);
		        message.addRecipients(Message.RecipientType.CC, copyDest);
		    } catch (MessagingException e) {
		        e.printStackTrace();
		    }
		    
		 // 3 -> Envoi du message
		    Transport transport = null;
		    try {
		        transport = session.getTransport("smtp");
		        transport.connect(LOGIN_SMTP1, PASSWORD_SMTP1);
		        transport.sendMessage(message, new Address[] { new InternetAddress(destinataire),
		                                                        new InternetAddress(copyDest) });
		    } catch (MessagingException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (transport != null) {
		                transport.close();
		            }
		        } catch (MessagingException e) {
		            e.printStackTrace();
		        }
		    }

		}
	

	@Override
	public void sendNotificationToAClient(String message, long clientID) {
		Notification myNotif = new Notification();
		myNotif.setMessage(message);
		
		Client myClient = findCliById(clientID);
		List<Notification> myNotifs = myClient.getNotifications();
		myNotifs.add(myNotif);
		myClient.setNotifications(myNotifs);	
	}

}
