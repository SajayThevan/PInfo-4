package domain.service;

import java.util.List;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import domain.model.Ingredient;
import domain.model.IngredientDTO;

@ApplicationScoped
public class IngredientServiceImpl implements IngredientService {
	
	private static final String failureMessage = "Ingredient does not exist : ";
	
	@PersistenceContext(unitName = "IngredientPU")
	private EntityManager em;

	public IngredientServiceImpl() {
	}
	
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
	public List<IngredientDTO> getSelectedIngredients(List<Long> ingredientID) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Ingredient> criteria = builder.createQuery(Ingredient.class);
		criteria.from(Ingredient.class);
		List<Ingredient> allIngredients = em.createQuery(criteria).getResultList();
		List<IngredientDTO> allIngredientsToSend = new ArrayList<>(); 
		for(Ingredient i : allIngredients) {
			if (ingredientID.contains(i.getId())) {
				IngredientDTO pair = new IngredientDTO(i.getId(), i.getName());
				allIngredientsToSend.add(pair);
			}
		}
		return allIngredientsToSend;
	}

	@Override
	public double computeCalories(List<Long> ingredientID) {
		double totalCalories = 0;
		for (Long id : ingredientID) {
			// take the kcal : 
			Ingredient ing = em.find(Ingredient.class, id);
			if (ing == null) {
				throw new IllegalArgumentException(failureMessage + id);
			}
			totalCalories += ing.getKcal();
		}
		return totalCalories;
	}
	
	@Override
	public double computeFat(List<Long> ingredientID) {
		double totalFat = 0;
		for (Long id : ingredientID) {
			// take the kcal : 
			Ingredient ing = em.find(Ingredient.class, id);
			if (ing == null) {
				throw new IllegalArgumentException(failureMessage + id);
			}
			totalFat += ing.getFat();
		}
		return totalFat;
	}
	
	@Override
	public double computeProteins(List<Long> ingredientID) {
		double totalProteins = 0;
		for (Long id : ingredientID) {
			// take the kcal : 
			Ingredient ing = em.find(Ingredient.class, id);
			if (ing == null) {
				throw new IllegalArgumentException(failureMessage + id);
			}
			totalProteins += ing.getProtein();
		}
		return totalProteins;
	}
	
	@Override
	public double computeCholesterol(List<Long> ingredientID) {
		double totalCholesterol = 0;
		for (Long id : ingredientID) {
			// take the kcal : 
			Ingredient ing = em.find(Ingredient.class, id);
			if (ing == null) {
				throw new IllegalArgumentException(failureMessage + id);
			}
			totalCholesterol += ing.getCholesterol();
		}
		return totalCholesterol;
	}
	
	@Override
	public double computeSalt(List<Long> ingredientID) {
		double totalSalt = 0;
		for (Long id : ingredientID) {
			// take the salt : 
			Ingredient ing = em.find(Ingredient.class, id);
			if (ing == null) {
				throw new IllegalArgumentException(failureMessage + id);
			}
			totalSalt += ing.getSalt();
		}
		return totalSalt;
	}
	
	@Override
	public void create(Ingredient ingredient) {
		if (ingredient.getId() != null) {
			throw new IllegalArgumentException(failureMessage + ingredient.getId());
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
