package domain.service;


import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import domain.model.Profile;
import domain.model.RecipeFav;
import domain.model.Ingredient;



@ApplicationScoped
public class ProfileServiceImpl implements ProfileService {
	
	@PersistenceContext(unitName = "ProfilePU") 
	private EntityManager em;

	public ProfileServiceImpl() {
	}

	public ProfileServiceImpl(EntityManager em) {
		this();
		this.em = em;
	}
	
	@Override				
	public List<Profile> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Profile> criteria = builder.createQuery(Profile.class);
		criteria.from(Profile.class);
		return em.createQuery(criteria).getResultList();
	}
	
	@Override
	@Transactional
	public void update(Profile profile) {
		Profile p = em.find(Profile.class, profile.getId());
		if (p == null) {
			throw new IllegalArgumentException("Profile does not exist : " + profile.getId());
		}
		em.merge(profile);
	}

	@Override
	@Transactional
	public void addIngredient(String id, long ingredientId, int quantity) {
		Profile p = get(id);
		Ingredient ing = new Ingredient();
		ing.setIngredientId(ingredientId);
		ing.setQuantity(quantity);
		Set <Ingredient> oldIngredients = p.getFridgeContents();
		oldIngredients.add(ing);
		p.setFridgeContents(oldIngredients);
		em.merge(p);
	}

	@Override
	@Transactional
	public void addFavourite(String id, long recipeId) {
		Profile p = get(id);
		RecipeFav fav = new RecipeFav();
		fav.setRecipeId(recipeId);
		Set <RecipeFav> oldFavourites = p.getFavouriteRecipes();
		oldFavourites.add(fav);
		p.setFavouriteRecipes(oldFavourites);
		em.merge(p);
	}
	
	@Override							
	public Profile get(String profileId) {
		return em.find(Profile.class, profileId);
	}

	@Override
	@Transactional
	public void create(Profile profile) {
		em.persist(profile);
	}
	
	@Override
	public Long count() {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Profile.class)));
		return em.createQuery(cq).getSingleResult();
	}
	
	@Override
	@Transactional
	public void removeProfile(String id) {
		Profile p = get(id);
		em.remove(p);
	}
	
	@Override
	@Transactional
	public void removeIngredient(String id,long ingredientId) {
		Profile p = get(id);
		Set <Ingredient> oldIngredients = p.getFridgeContents();
		Ingredient ing = new Ingredient();
		ing.setIngredientId(ingredientId);
		oldIngredients.remove(ing);
		p.setFridgeContents(oldIngredients);
		em.merge(p);
	}
	
	@Override
	@Transactional
	public void removeFavourite(String id,long recipeId) {
		Profile p = get(id);
		Set <RecipeFav> oldFavourites = p.getFavouriteRecipes();
		RecipeFav fav = new RecipeFav();
		fav.setRecipeId(recipeId);
		oldFavourites.remove(fav);
		p.setFavouriteRecipes(oldFavourites);
		em.merge(p);
	}
	
	@Override
	public boolean checkProfile(String profileId) {
		Profile p = em.find(Profile.class, profileId);
		if (p == null) {
			return false;
		}
		else {
			return true;
		}
		
	
		
	}
	
}
