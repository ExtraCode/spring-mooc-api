package com.mooc.ludotheque.services;

import java.util.List;

import com.mooc.ludotheque.models.Entreprise;

public interface EntrepriseService {
	
	public List<Entreprise> findAll();

	public Entreprise findById(int id);

	public int create(Entreprise entreprise);

	public void update(int id, Entreprise entreprise);

	public void updatePartial(Entreprise entrepriseExistante, Entreprise newEntreprise);

	public void delete(int id);

}
