package com.wha.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wha.springmvc.dao.JustificatifDao;
import com.wha.springmvc.model.Justificatif;

@Service("justificatifService")
@Transactional
public class JustificatifServiceImpl implements JustificatifService {

	
	@Autowired
	private JustificatifDao dao;
	
	
	@Override
	public List<Justificatif> findById(long id,int clientOuDemande){
return dao.findById(id, clientOuDemande);
	}

	@Override
	public void saveJustificatif(long id_demandeouclient, Justificatif justificatif,int clientOuDemande){
		dao.saveJustificatif(id_demandeouclient, justificatif, clientOuDemande);

	}

	@Override
	public void deleteJustificatifById(long id) {
		// TODO Auto-generated method stub

	}
	//#region Attributs

	//#endregion

	//#region Constructeur

	//#endregion

	//#region Methode

	//#endregion

}
