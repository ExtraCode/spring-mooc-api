package com.mooc.ludotheque.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mooc.ludotheque.exceptions.EntityDontExistException;
import com.mooc.ludotheque.models.Entreprise;
import com.mooc.ludotheque.services.EntrepriseService;

@RestController
public class EntrepriseController {

	@Autowired
	private EntrepriseService entrepriseService;

	@GetMapping("/entreprises")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Entreprise> findAll() {
		
		List<Entreprise> entreprises = entrepriseService.findAll();
		for(Entreprise entreprise : entreprises) {
			
			// j'ajoute un link
			Link selflink = WebMvcLinkBuilder.linkTo(EntrepriseController.class).slash("entreprise").slash(entreprise.getId()).withSelfRel();
			entreprise.add(selflink);
			
		}

		return entrepriseService.findAll();

	}

	@GetMapping("/entreprise/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Entreprise findById(@PathVariable("id") int id) {
		
		return entrepriseService.findById(id);

	}

	@PostMapping("/entreprise")
	@ResponseStatus(code = HttpStatus.CREATED)
	public int create(@RequestBody Entreprise entreprise) {

		return entrepriseService.create(entreprise);

	}

	@PutMapping("/entreprise/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void update(@PathVariable int id, @RequestBody Entreprise entreprise) {

		// Contrôle si l'entité existe
		entrepriseService.findById(id);
		entrepriseService.update(id, entreprise);

	}

	@PatchMapping("/entreprise/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void updatePartial(@PathVariable int id, @RequestBody Entreprise newEntreprise) {

		Entreprise entrepriseExistante = entrepriseService.findById(id);

		entrepriseService.updatePartial(entrepriseExistante, newEntreprise);

	}

	@DeleteMapping("/entreprise/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable int id) {

		// Contrôle si l'entité existe
		entrepriseService.findById(id);
		entrepriseService.delete(id);

	}

}