package com.mooc.ludotheque.models;

import java.util.HashSet;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name="entreprise")
@Entity
public class Entreprise extends RepresentationModel<Entreprise> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String nom;
	private String ville;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Jeu> jeux = new HashSet<Jeu>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public Set<Jeu> getJeux() {
		return jeux;
	}
	public void setJeux(Set<Jeu> jeux) {
		this.jeux = jeux;
	}	

}