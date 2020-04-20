package domain.service;

import java.util.List;
import java.util.ArrayList;

import java.io.*; 
import java.lang.*; 

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import domain.model.Ingredient;

@ApplicationScoped
public class IngredientServiceImpl implements IngredientService {

	// TODO: Implement
	
	@PersistenceContext(unitName = "IngredientPU")
	private EntityManager em;

	public IngredientServiceImpl() {
	}
	
	// https://docs.oracle.com/javaee/7/api/javax/persistence/EntityManager.html

	public IngredientServiceImpl(EntityManager em) {
		this();
		this.em = em;
	}
	
	@Override
	public List<Ingredient> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ingredient> criteria = builder.createQuery(Ingredient.class);
		criteria.from(Ingredient.class);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public Ingredient get(Long id) {
		return em.find(Ingredient.class, id);
	}

	@Override
	public int computeCalories(List<Long> IngredientID) {
		int totalCalories = 0;
		for (Long id : IngredientID) {
			// take the kcal : 
			Ingredient ing = em.find(Ingredient.class, id);
			if (ing == null) {
				throw new IllegalArgumentException("Ingredient does not exist : " + id);
			}
			totalCalories += ing.getKcal();
		}
		return totalCalories;
	}
	
	// return every ingredient containing possibleIngredient in their name 
	// return : Array<(IngredientID, Name)>
	@Override
	public List<Object> getPossibleIngredients(String possibleIngredient) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ingredient> criteria = builder.createQuery(Ingredient.class);
		criteria.from(Ingredient.class);
		List<Ingredient> allIngredients = em.createQuery(criteria).getResultList();
		List<Object> possibilities = new ArrayList<>(); 
		
		for(Ingredient i : allIngredients) {
			String nameIngredient = i.getName();
			if (nameIngredient.contains(possibleIngredient)) {
				List<Object> elt = new ArrayList<>(); 
				elt.add(i.getId());
				elt.add(nameIngredient);
				possibilities.add(elt);
			}
		}
		
		return possibilities;
	}
	
	@Override
	public void create(Ingredient ingredient) {
		if (ingredient.getId() != null) {
			throw new IllegalArgumentException("Instrument already exists : " + ingredient.getId());
		}
		em.persist(ingredient);
	}
	/*
	@Override
	public List<Instrument> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Instrument> criteria = builder.createQuery(Instrument.class);
		criteria.from(Instrument.class);
		return em.createQuery(criteria).getResultList();
	}
	*/
}
