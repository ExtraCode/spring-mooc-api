package com.mooc.ludotheque.dao;

import org.springframework.data.repository.CrudRepository;

import com.mooc.ludotheque.models.Jeu;

public interface JeuRepository extends CrudRepository<Jeu, Integer> {

}