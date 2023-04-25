package com.mooc.ludotheque.services;

import java.util.Set;

import com.mooc.ludotheque.models.Jeu;

public interface JeuService {

	Set<Jeu> findAllByEntreprise(int id_entreprise);

	Jeu findById(int id_jeu);

	int create(int id_entreprise, Jeu jeu);

	void update(int id_jeu, Jeu jeu);

	void updatePartial(Jeu jeuExistant, Jeu newJeu);

	void delete(Jeu jeu);



}