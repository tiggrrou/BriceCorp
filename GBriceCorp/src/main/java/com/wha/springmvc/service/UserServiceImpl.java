package com.wha.springmvc.service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wha.springmvc.dao.UserDao;
import com.wha.springmvc.model.Administrateur;
import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationInfo;
import com.wha.springmvc.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@SuppressWarnings("unused")
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
	public void updateclient(Dem_ModificationInfo demande_modificationinfoperso){
dao.updateclient(demande_modificationinfoperso);

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
	public Client generationMdp(long clientID){
	

		return dao.generationMdp(clientID);
	}

	@Override
	public boolean checkConseillerIdentifiant(String identifiant) {
		return dao.checkConseillerIdentifiant(identifiant);
		
	}

	@Override
	public void modifEtat_Notif(long userID, long notifID) {
		dao.modifEtat_Notif(userID,notifID );
		
	}

	
}
