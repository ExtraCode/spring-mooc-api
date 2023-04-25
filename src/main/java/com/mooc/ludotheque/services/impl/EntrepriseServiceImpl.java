package com.mooc.ludotheque.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mooc.ludotheque.dao.EntrepriseRepository;
import com.mooc.ludotheque.exceptions.EntityDontExistException;
import com.mooc.ludotheque.models.Entreprise;
import com.mooc.ludotheque.services.EntrepriseService;

@Service
public class EntrepriseServiceImpl implements EntrepriseService {
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@Override
	public List<Entreprise> findAll() {
		
		List<Entreprise> entreprises = new ArrayList<Entreprise>();
		entrepriseRepository.findAll().forEach(entreprises::add);
		return entreprises;
	}

	@Override
	public Entreprise findById(int id) {
		
		Optional<Entreprise> entreprise = entrepriseRepository.findById(id);
		
		if(entreprise.isPresent()) {
			return entreprise.get();
		}
		
		throw new EntityDontExistException();
		
	}

	@Override
	public int create(Entreprise entreprise) {
		
		return entrepriseRepository.save(entreprise).getId();
		
	}

	@Override
	public void update(int id, Entreprise entreprise) {
		
		entreprise.setId(id);
		
		entrepriseRepository.save(entreprise);
		
	}

	@Override
	public void updatePartial(Entreprise entrepriseExistante, Entreprise newEntreprise) {
		
		// Mettre à jour les champs de l'entreprise
		if(newEntreprise.getNom() != null) {
			entrepriseExistante.setNom(newEntreprise.getNom());
		}
		
		if(newEntreprise.getVille() != null) {
			entrepriseExistante.setVille(newEntreprise.getVille());
		}
		
		// Mettre à jour les champs de l'entreprise
		entrepriseRepository.save(entrepriseExistante);
		
	}

	@Override
	public void delete(int id) {
		
		entrepriseRepository.deleteById(id);
		
	}

}
