package com.mooc.ludotheque.services.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mooc.ludotheque.dao.JeuRepository;
import com.mooc.ludotheque.exceptions.EntityDontExistException;
import com.mooc.ludotheque.models.Entreprise;
import com.mooc.ludotheque.models.Jeu;
import com.mooc.ludotheque.services.JeuService;

@Service
public class JeuServiceImpl implements JeuService {

	@Autowired
	private JeuRepository jeuRepository;
	
	@Autowired
	private EntrepriseServiceImpl entrepriseService;
	
	@Override
	public Set<Jeu> findAllByEntreprise(int id_entreprise) {
		
		return entrepriseService.findById(id_entreprise).getJeux();
		
	}

	@Override
	public Jeu findById(int id_jeu) {
		
		Optional<Jeu> jeu = jeuRepository.findById(id_jeu);
		
		// Si je trouve le jeu
		if(jeu.isPresent()) {
			return jeu.get();
		}
		
		throw new EntityDontExistException();
		
	}

	@Override
	public int create(int id_entreprise, Jeu jeu) {
		
		// Récupération de l'entreprise
		Entreprise entreprise = entrepriseService.findById(id_entreprise);
		
		// Ajouter l'entreprise au jeu
		jeu.setEntreprise(entreprise);
		
		// Mise à jour de la liste des jeux de l'entreprise
		entreprise.getJeux().add(jeu);
		
		// Sauvegarde en base
		return jeuRepository.save(jeu).getId();
		
	}

	@Override
	public void update(int id_jeu, Jeu jeu) {
		
		jeu.setId(id_jeu);
		jeuRepository.save(jeu);
		
	}

	@Override
	public void updatePartial(Jeu jeuExistant, Jeu newJeu) {
		
		// Mettre à jour les champs modifiables
		if(newJeu.getNom() != null) {
			jeuExistant.setNom(newJeu.getNom());
		}
		
		if(newJeu.getAgeMini() != 0) {
			jeuExistant.setAgeMini(newJeu.getAgeMini());
		}
		
		if(newJeu.getDateSortie() != null) {
			jeuExistant.setDateSortie(newJeu.getDateSortie());
		}
		
		// Mettre à jour le jeu existant dans la base
		jeuRepository.save(jeuExistant);
		
	}

	@Override
	public void delete(Jeu jeu) {
		
		jeu.getEntreprise().getJeux().remove(jeu);
		jeuRepository.delete(jeu);
		
	}

}
