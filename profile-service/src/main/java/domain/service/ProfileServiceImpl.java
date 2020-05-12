package domain.service;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

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
	public void update(Profile profile) {
		Profile p = em.find(Profile.class, profile.getId());
		if (p == null) {
			throw new IllegalArgumentException("Profile does not exist : " + profile.getId());
		}
		em.merge(profile);
	}
	
	@Override
	public void addIngredient(long id, long ingredientId, int quantity) {
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
	public void addFavourite(long id, long recipeId) {
		Profile p = get(id);
		RecipeFav fav = new RecipeFav();
		fav.setRecipeId(recipeId);
		Set <RecipeFav> oldFavourites = p.getFavouriteRecipes();
		oldFavourites.add(fav);
		p.setFavouriteRecipes(oldFavourites);
		em.merge(p);
	}
	
	@Override							
	public Profile get(Long profileId) {
		return em.find(Profile.class, profileId);
	}

	@Override
	public void create(Profile profile) {
		if (profile.getId() != null) {
			throw new IllegalArgumentException("Profile already exists : " + profile.getId());
		}
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
	public void removeProfile(long id) {
		Profile p = get(id);
		em.remove(p);
	}
	
	@Override
	public void removeIngredient(long id,long ingredientId,int quantity) {
		Profile p = get(id);
		Set <Ingredient> oldIngredients = p.getFridgeContents();
		Ingredient ing = new Ingredient();
		ing.setIngredientId(ingredientId);
		ing.setQuantity(quantity);
		oldIngredients.remove(ing); // Doute id primaire nessaisaire?
		p.setFridgeContents(oldIngredients);
		em.merge(p);
		
		
	}
	
	@Override
	public void removeFavourite(long id,long recipeId) {
		Profile p = get(id);
		Set <RecipeFav> oldFavourites = p.getFavouriteRecipes();
		RecipeFav fav = new RecipeFav();
		fav.setRecipeId(recipeId);
		oldFavourites.remove(fav); // Doute id primaire nessaisaire?
		p.setFavouriteRecipes(oldFavourites);
		em.merge(p);
		
		
	}
	
	
}
