package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.CompteRemunerateur;

public interface CompteDao {

	
	Compte findCById(long compteid);
	
	List<Compte> findCByClientId(long clientId);
	
	public long saveCompte(Compte compte);
	
	void updateCompte(Compte compte);
	
	void deleteCompteById(long compteid);

	List<Compte> findAllComptes(); 
	
	void deleteAllComptes();
	
	
	
	Compte findCRById(long compteremid);
	
	List<CompteRemunerateur> findCRByClientId(long compteremid);
	
	void saveCompteRemunerateur(CompteRemunerateur compteremunerateur);
	
	void updateCompteRemunerateur(CompteRemunerateur compteremunerateur);
	
	void deleteCompteRemunerateurById(long compteremid);

	List<CompteRemunerateur> findAllComptesRemunerateur(); 
	
	void deleteAllComptesRemunerateur();
}
