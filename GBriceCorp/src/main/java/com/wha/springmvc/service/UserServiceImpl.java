package com.wha.springmvc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.Dem_Chequier;
import com.wha.springmvc.model.Dem_CreationClient;
import com.wha.springmvc.model.Dem_ModificationCompte;

import com.wha.springmvc.model.Justificatif;
import com.wha.springmvc.model.TypeJustificatif;
import com.wha.springmvc.model.TypeUtilisateur;
import com.wha.springmvc.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;
	private static List<Client> clients;
	private static List<Conseiller> conseillers;

	static {
		users = populateDummyUsers();
		conseillers = populateDummyConseillers();
		clients = populateDummyClients();

	}

	public List<User> findAllUsers() {
		return users;
	}

	public User findById(long id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User trouveParNom(String nom) {
		for (User user : users) {
			if (user.getNom().equalsIgnoreCase(nom)) {
				return user;
			}
		}
		return null;
	}

	public User trouveParPrenom(String prenom) {
		for (User user : users) {
			if (user.getPrenom().equalsIgnoreCase(prenom)) {
				return user;
			}
		}
		return null;
	}

	public User trouveParMail(String mail) {
		for (User user : users) {
			if (user.getMail().equalsIgnoreCase(mail)) {
				return user;
			}
		}
		return null;
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public List<User> trouveParType(int typeUser) {
		List<User> listeusers = new ArrayList<User>();

		for (User user : users) {
			if (user.getTypeUser() == typeUser) {
				listeusers.add(user);
			}
		}
		return listeusers;
	}

	/**
	 * Recherche un utilisateur par son login
	 * 
	 * @param login
	 * @return null si non trouvé
	 */
	public User findByLogin(String login) {
		for (User user : users) {
			if (user.getIdentifiant().equalsIgnoreCase(login)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Vérifie le mot de passe d'un utilisateur
	 * 
	 * @param user
	 * @param mdp
	 * @return vrai si les valeurs concordent
	 */
	public boolean checkPassword(User user, String mdp) {
		if (user != null) {
			return (user.getMotDePasse().equalsIgnoreCase(mdp));
		}
		return false;
	}

	public String connexion(String login, String mdp) {
		User l_user = findByLogin(login);

		if (checkPassword(l_user, mdp)) {
			return "identification réussie";
		}
		return "identification incorrecte";
	}

	public void deleteUserById(long id) {

		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
	}

	public boolean isUserExist(User user) {
		return trouveParMail(user.getMail()) != null;
	}

	public void deleteAllUsers() {
		users.clear();
	}

	private static List<User> populateDummyUsers() {
		users = new ArrayList<User>();
		/*
		 * users.add(new User(counter.incrementAndGet(), "Sam", "PARIS",
		 * "sam@abc.com", "a", "a",TypeUtilisateur.Administrateur.getType()));
		 */
		users.add(new User(3, "Sam", "Sam", "PARIS", "sam@abc.com", "a", "a", TypeUtilisateur.Administrateur.getType(),
				546));
		users.add(new User(1, "wajih", "wajih", "rue albert 1er COLOMBES", "wajih@formation.com", "b", "b",
				TypeUtilisateur.Conseiller.getType(), 564654));
		users.add(new User(2, "Tomy", "Tomy", "ALBAMA", "tomy@abc.com", "c", "c", TypeUtilisateur.Client.getType(),
				654654));
		users.add(new User(4, "Sophie", "Sophie", "TEXAS", "sophie@def.com", "d", "d", TypeUtilisateur.Client.getType(),
				564654));
		users.add(new User(5, "John", "John", "CALIFORNIA", "john@ijk.com", "e", "e", TypeUtilisateur.Client.getType(),
				65465));
		users.add(new User(6, "prenom cons2", "nom cons2", "rue machion truc", "wajih@formation.com", "f", "f",
				TypeUtilisateur.Conseiller.getType(), 56464));
		users.add(new User(7, "Conseiller3", "dsfdfge", "rue rgrege", "wajih@formation.com", "g", "g",
				TypeUtilisateur.Conseiller.getType(), 564645));
		return users;

	}

	private static List<Conseiller> populateDummyConseillers() {
		conseillers = new ArrayList<Conseiller>();

		conseillers.add(new Conseiller(1, "wajih", "wajih", "rue albert 1er COLOMBES", "wajih@formation.com", "b", "b",
				TypeUtilisateur.Conseiller.getType(), 564654, new Date(), new Date()));
		conseillers.add(new Conseiller(6, "prenom cons2", "nom cons2", "rue machion truc", "wajih@formation.com", "f",
				"f", TypeUtilisateur.Conseiller.getType(), 56464, new Date(), new Date()));
		conseillers.add(new Conseiller(7, "Conseiller3", "dsfdfge", "rue rgrege", "wajih@formation.com", "g", "g",
				TypeUtilisateur.Conseiller.getType(), 564645, new Date(), new Date()));
		return conseillers;

	}

	private static List<Client> populateDummyClients() {
		clients = new ArrayList<Client>();

		clients.add(new Client(2, "Tomy", "Tomy", "ALBAMA", "tomy@abc.com", "c", "c", TypeUtilisateur.Client.getType(),
				654654, new Date(), new Date(), 0));
		clients.add(new Client(4, "Sophie", "Sophie", "TEXAS", "sophie@def.com", "d", "d",
				TypeUtilisateur.Client.getType(), 564654, new Date(), new Date(), 0));
		clients.add(new Client(5, "John", "John", "CALIFORNIA", "john@ijk.com", "e", "e",
				TypeUtilisateur.Client.getType(), 65465, new Date(), new Date(), 0));
		return clients;

	}

	@Override
	public void saveConseiller(User user) {
		int consMatricule =(int) counter.incrementAndGet();
		Date dateDeb = new Date();
		Date dateFin = null;
		Conseiller conseiller = new Conseiller (user,consMatricule, dateDeb, dateFin);
		users.add(conseiller);
		conseillers.add(conseiller);
	}

	@Override
	public List<User> getUser_Cons(long id) {
		return trouveParType(3);
	}

	@Override
	public List<User> getUser_Admin_Cons() {
		return trouveParType(2);
	}

}
