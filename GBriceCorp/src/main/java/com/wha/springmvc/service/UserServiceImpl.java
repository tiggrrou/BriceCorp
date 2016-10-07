package com.wha.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;


import com.wha.springmvc.model.TypeUtilisateur;
import com.wha.springmvc.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;

	static {
		users = populateDummyUsers();
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

	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
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
		
		
		
		return trouveParNom(user.getNom()) != null && trouveParPrenom(user.getPrenom()) != null;
	}

	public void deleteAllUsers() {
		users.clear();
	}

	private static List<User> populateDummyUsers() {
		List<User> users = new ArrayList<User>();
		/*users.add(new User(counter.incrementAndGet(), "Sam", "PARIS", "sam@abc.com", "a", "a",TypeUtilisateur.Administrateur.getType()));*/
		users.add(new User(3, "Sam", "Sam","PARIS", "sam@abc.com", "a", "a",TypeUtilisateur.Administrateur.getType()));
		users.add(new User(1, "wajih","wajih", "rue albert 1er COLOMBES", "wajih@formation.com", "b",
				"b", TypeUtilisateur.Conseiller.getType()));
		users.add(new User(2, "Tomy","Tomy", "ALBAMA", "tomy@abc.com", "c", "c",TypeUtilisateur.Client.getType()));
		users.add(new User(4, "Sophie","Sophie", "TEXAS", "sophie@def.com", "d", "d",TypeUtilisateur.Client.getType()));
		users.add(new User(5, "John","John", "CALIFORNIA", "john@ijk.com", "e", "e",TypeUtilisateur.Client.getType()));
		users.add(new User(6, "prenom cons2","nom cons2", "rue machion truc", "wajih@formation.com", "f",
				"f", TypeUtilisateur.Conseiller.getType()));
		users.add(new User(7, "Conseiller3","dsfdfge", "rue rgrege", "wajih@formation.com", "g",
				"g", TypeUtilisateur.Conseiller.getType()));
		return users;
	}
	
	@Override
	public void saveConseiller(Conseiller conseiller) {
		conseiller.setId(counter.incrementAndGet());
		conseiller.setMatricule((int) counter.incrementAndGet());
		users.add(conseiller);
		
	}

}
