package com.wha.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.CompteRemunerateur;

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
	public void saveCompte(Compte compte) {
		persist(compte);
		
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

	@Override
	public Compte findCRById(long compteremid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompteRemunerateur> findCRByClientId(long compteremid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCompteRemunerateur(CompteRemunerateur compteremunerateur) {
		persist(compteremunerateur);
	}

	@Override
	public void updateCompteRemunerateur(CompteRemunerateur compteremunerateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCompteRemunerateurById(long compteremid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CompteRemunerateur> findAllComptesRemunerateur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllComptesRemunerateur() {
		// TODO Auto-generated method stub
		
	}

	

}
