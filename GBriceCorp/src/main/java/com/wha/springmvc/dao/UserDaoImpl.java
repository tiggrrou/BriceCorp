package com.wha.springmvc.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Conseiller;
import com.wha.springmvc.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	@Override
	public void createConseiller(Conseiller conseiller) {
		persist(conseiller);

	}

	@Override
	public void deleteConseiller(long idCons) {
		Conseiller conseiller = findConsById(idCons);
		delete(conseiller);
	}

	@Override
	public void updateConseiller(Conseiller conseiller) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Conseiller> findAllConseillers() {
		@SuppressWarnings("unchecked")
		List<Conseiller> conseillers = getEntityManager().createQuery("FROM Conseiller").getResultList();
		return conseillers;
	}

	@Override
	public Conseiller findConsById(long idCons) {
		try {
			Conseiller conseiller = (Conseiller) getEntityManager().createQuery("SELECT c FROM Conseiller c WHERE c.id = id")
					.setParameter("id", idCons).getSingleResult();
			return conseiller;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public void createClient(Client client) {
		persist(client);

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
		List<Client> client = getEntityManager().createQuery("FROM Client").getResultList();
		return client;
	}

	@Override
	public Client findCliById(long idcli) {
		try {
			Client client = (Client) getEntityManager().createQuery("SELECT c FROM Client c WHERE c.id = id")
					.setParameter("id", idcli).getSingleResult();
			return client;
		} catch (NoResultException ex) {
			return null;
		}
	}

}
