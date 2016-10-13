package com.wha.springmvc.dao;

import java.util.List;

import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.CompteRemunerateur;

public interface CompteDao {

	
	Compte findCById(String compteid);
	
	List<Compte> findCByClientId(long clientId);
	
	void saveCompte(Compte compte);
	
	void updateCompte(Compte compte);
	
	void deleteCompteById(String compteid);

	List<Compte> findAllComptes(); 
	
	void deleteAllComptes();
	
	
	
	Compte findCRById(String compteremid);
	
	List<CompteRemunerateur> findCRByClientId(long compteremid);
	
	void saveCompteRemunerateur(CompteRemunerateur compteremunerateur);
	
	void updateCompteRemunerateur(CompteRemunerateur compteremunerateur);
	
	void deleteCompteRemunerateurById(String compteremid);

	List<CompteRemunerateur> findAllComptesRemunerateur(); 
	
	void deleteAllComptesRemunerateur();
}
