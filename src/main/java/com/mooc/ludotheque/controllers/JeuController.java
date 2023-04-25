package com.mooc.ludotheque.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mooc.ludotheque.models.Jeu;
import com.mooc.ludotheque.services.JeuService;

@RestController
public class JeuController {
	
	@Autowired
	private JeuService jeuService;
	
	@GetMapping("/entreprise/{id_entreprise}/jeux")
	@ResponseStatus(code = HttpStatus.OK)
	public Set<Jeu> findAllByEntreprise(@PathVariable("id_entreprise") int id_entreprise){
		
		return jeuService.findAllByEntreprise(id_entreprise);
		
	}
	
	@GetMapping("/jeu/{id_jeu}")
	@ResponseStatus(code = HttpStatus.OK)
	public Jeu findById(@PathVariable("id_jeu") int id_jeu) {
		
		return jeuService.findById(id_jeu);
		
	}
	
	@PostMapping("/entreprise/{id_entreprise}/jeu")
	@ResponseStatus(code = HttpStatus.CREATED)
	public int cree(@PathVariable("id_entreprise") int id_entreprise, @RequestBody Jeu jeu) {
		
		return jeuService.create(id_entreprise,jeu);
		
	}
	
	@PutMapping("/jeu/{id_jeu}")
	@ResponseStatus(code = HttpStatus.OK)
	public void modifier(@PathVariable("id_jeu") int id_jeu, @RequestBody Jeu jeu) {
		
		// Si le jeu n'existe pas
		jeuService.findById(id_jeu);
		
		jeuService.update(id_jeu,jeu);
		
	}
	
	@PatchMapping("/jeu/{id_jeu}")
	@ResponseStatus(code = HttpStatus.OK)
	public void modifierPartiel(@PathVariable("id_jeu") int id_jeu, @RequestBody Jeu newJeu) {
		
		Jeu jeuExistant = jeuService.findById(id_jeu);
		
		jeuService.updatePartial(jeuExistant,newJeu);
		
	}
	
	@DeleteMapping("/jeu/{id_jeu}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable("id_jeu") int id_jeu) {
		
		Jeu jeu = jeuService.findById(id_jeu);
		
		jeuService.delete(jeu);
		
	}
	

}
