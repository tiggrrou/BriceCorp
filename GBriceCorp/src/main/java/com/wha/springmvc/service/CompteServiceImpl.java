package com.wha.springmvc.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wha.springmvc.dao.CompteDao;
import com.wha.springmvc.model.Compte;

@Service("compteService")
@Transactional
public class CompteServiceImpl implements CompteService {

	@Autowired
	private CompteDao dao;

	private static final AtomicLong counter = new AtomicLong();

	public List<Compte> findAllComptes() {
return dao.findAllComptes();
	}

	public Compte findById(long compteid) {
		return dao.findCById(compteid);
	}

	public List<Compte> findByClientId(long clientId) {
		return dao.findCByClientId(clientId);
	}

	public Compte findByLibelle(String name) {
//
//		for (Compte compte : comptes) {
//			if (compte.getLibelle().equalsIgnoreCase(name)) {
//				return compte;
//			}
//		}
		return null;
	}

	public long saveCompte(Compte compte) {
		return dao.saveCompte(compte);
	}

	public void updateCompte(Compte compte) {
//		int index = comptes.indexOf(compte);
//		comptes.set(index, compte);
	}

	public void deleteCompteById(long compteid) {

		dao.deleteCompteById(compteid);
	}
// ce serai pas plutot une comparaison sur le libelle et l'idclient ?
	public boolean isCompteExist(Compte compte) {
		return dao.findCById(compte.getID()) != null;
	}

	public void deleteAllComptes() {
		dao.deleteAllComptes();
	}

}
