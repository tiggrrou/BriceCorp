package com.wha.springmvc.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.wha.springmvc.model.Client;
import com.wha.springmvc.model.Compte;
import com.wha.springmvc.model.Mouvement;

@Repository("compteDao")
public class CompteDaoImpl extends AbstractDao<Integer, Compte> implements CompteDao {

	@Override
	public Compte findCById(long compteid) {
		try {
			Compte compte = (Compte) getEntityManager()
					.createQuery("SELECT C FROM Compte C WHERE C.id = :id").setParameter("id", compteid)
					.getSingleResult();
			return compte;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public List<Compte> findCByClientId(long clientId) {
		@SuppressWarnings("unchecked")
		List<Compte> comptes = getEntityManager().createQuery("SELECT c.comptes FROM Client c WHERE c.id = :id")
				.setParameter("id", clientId)
				.getResultList();
		return comptes;
	}

	@Override
	public long saveCompte(Compte compte) {
		persist(compte);
		return compte.getID();
	}

	@Override
	public void updateCompte(Compte compte) {
		Compte cpt = findCById(compte.getID());
		cpt.setActif(compte.isActif());
		cpt.setDateCloture(compte.getDateCloture());
		cpt.setDateOuverture(compte.getDateOuverture());
		cpt.setDecouvert(compte.getDecouvert());
		cpt.setLibelle(compte.getLibelle());
		cpt.setMouvements(compte.getMouvements());
		cpt.setSeuil(compte.getSeuil());
		cpt.setSolde(compte.getSolde());
		cpt.setSoldeAgio(compte.getSoldeAgio());
		cpt.setSoldeRemuneration(compte.getSoldeRemuneration());
		cpt.setTauxDecouvert(compte.getTauxDecouvert());
		cpt.setTauxRemuneration(compte.getTauxRemuneration());
		
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
	public void mouvement(float montant, long compteDebiteurID, long compteCrediteurID) {
		
		Compte compteDebite = findCById(compteDebiteurID);
		//opération de débit
		compteDebite.setSolde(compteDebite.getSolde() - montant);
		//ajout du mouvement
		List<Mouvement> listMvtDebit = compteDebite.getMouvements();
		Mouvement mvtDebit = new Mouvement();
		mvtDebit.setMontant(-montant);
		mvtDebit.setLibelle("Debit vers compte " + compteCrediteurID);
		listMvtDebit.add(mvtDebit);
		compteDebite.setMouvements(listMvtDebit);
		
		
		Compte compteCredite = findCById(compteCrediteurID);
		//opération de crédit
		compteCredite.setSolde(compteCredite.getSolde() + montant);
		//ajout du mouvement
		List<Mouvement> listMvtCredit = compteCredite.getMouvements();
		Mouvement mvtCredit = new Mouvement();
		mvtCredit.setMontant(montant);
		mvtCredit.setLibelle("Credit depuis compte " + compteDebiteurID);
		listMvtCredit.add(mvtCredit);
		compteCredite.setMouvements(listMvtCredit);
		
		
	}

	@Override
	public Client findOwnerByCountID(long idCompte) {
		try {
			Client client = (Client) getEntityManager()
					.createQuery("SELECT C FROM Client C WHERE "
							+ "	(SELECT Co FROM Compte Co WHERE Co.id = :id)"
							+ " MEMBER OF C.comptes").setParameter("id", idCompte)
					.getSingleResult();
			return client;
		} catch (NoResultException ex) {
			return null;
		}
	}

	

}
