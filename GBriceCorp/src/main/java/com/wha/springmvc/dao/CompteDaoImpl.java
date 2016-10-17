package com.wha.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Compte;

@Repository("compteDao")
public class CompteDaoImpl extends AbstractDao<Integer, Compte> implements CompteDao {

	@Override
	public Compte findCById(long compteid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> findCByClientId(long clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long saveCompte(Compte compte) {
		persist(compte);
		return compte.getID();
	}

	@Override
	public void updateCompte(Compte compte) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCompteById(long compteid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Compte> findAllComptes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllComptes() {
		// TODO Auto-generated method stub
		
	}

	

}
