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
import domain.model.IngredientDTO;

@ApplicationScoped
public class IngredientServiceImpl implements IngredientService {
	
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
	public List<IngredientDTO> getAllNames() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ingredient> criteria = builder.createQuery(Ingredient.class);
		criteria.from(Ingredient.class);
		List<Ingredient> allIngredients = em.createQuery(criteria).getResultList();
		List<IngredientDTO> allIngredientsToSend = new ArrayList<>(); 
		for(Ingredient i : allIngredients) {
			IngredientDTO pair = new IngredientDTO(i.getId(), i.getName());
			allIngredientsToSend.add(pair);
		}
		return allIngredientsToSend;
	}


	@Override
	public Ingredient get(Long id) {
		return em.find(Ingredient.class, id);
	}
	
	@Override
	public List<IngredientDTO> getSelectedIngredients(List<Long> IngredientID) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ingredient> criteria = builder.createQuery(Ingredient.class);
		criteria.from(Ingredient.class);
		List<Ingredient> allIngredients = em.createQuery(criteria).getResultList();
		List<IngredientDTO> allIngredientsToSend = new ArrayList<>(); 
		for(Ingredient i : allIngredients) {
			if (IngredientID.contains(i.getId())) {
				IngredientDTO pair = new IngredientDTO(i.getId(), i.getName());
				allIngredientsToSend.add(pair);
			}
		}
		return allIngredientsToSend;
	}

	@Override
	public double computeCalories(List<Long> IngredientID) {
		double totalCalories = 0;
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
	
	@Override
	public double computeFat(List<Long> IngredientID) {
		double totalFat = 0;
		for (Long id : IngredientID) {
			// take the kcal : 
			Ingredient ing = em.find(Ingredient.class, id);
			if (ing == null) {
				throw new IllegalArgumentException("Ingredient does not exist : " + id);
			}
			totalFat += ing.getFat();
		}
		return totalFat;
	}
	
	@Override
	public double computeProteins(List<Long> IngredientID) {
		double totalProteins = 0;
		for (Long id : IngredientID) {
			// take the kcal : 
			Ingredient ing = em.find(Ingredient.class, id);
			if (ing == null) {
				throw new IllegalArgumentException("Ingredient does not exist : " + id);
			}
			totalProteins += ing.getProtein();
		}
		return totalProteins;
	}
	
	@Override
	public double computeCholesterol(List<Long> IngredientID) {
		double totalCholesterol = 0;
		for (Long id : IngredientID) {
			// take the kcal : 
			Ingredient ing = em.find(Ingredient.class, id);
			if (ing == null) {
				throw new IllegalArgumentException("Ingredient does not exist : " + id);
			}
			totalCholesterol += ing.getCholesterol();
		}
		return totalCholesterol;
	}
	
	@Override
	public double computeSalt(List<Long> IngredientID) {
		double totalSalt = 0;
		for (Long id : IngredientID) {
			// take the kcal : 
			Ingredient ing = em.find(Ingredient.class, id);
			if (ing == null) {
				throw new IllegalArgumentException("Ingredient does not exist : " + id);
			}
			totalSalt += ing.getSalt();
		}
		return totalSalt;
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
			throw new IllegalArgumentException("Ingredient already exists : " + ingredient.getId());
		}
		em.persist(ingredient);
	}
	
	@Override
	public Long count() {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Ingredient.class)));
		return em.createQuery(cq).getSingleResult();
	}
}
