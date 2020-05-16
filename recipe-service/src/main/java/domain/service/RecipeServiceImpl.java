package domain.service;


import java.lang.reflect.Array;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.Iterator;

import org.javatuples.Triplet; 
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import domain.model.CategoryEnum;
import domain.model.Comments;
import domain.model.Ingredients;
import domain.model.Recipe;
import domain.model.Ratings;
import lombok.Data;

@ApplicationScoped
public class RecipeServiceImpl implements RecipeService {
	
	@PersistenceContext(unitName = "RecipePU")
	private EntityManager em;
	
	
	public RecipeServiceImpl() {
	}

	public RecipeServiceImpl(EntityManager em) {
		this();
		this.em = em;
	}
	
	
	public void addRecipe(Recipe r) {
		if (r.getId() != null) {
			throw new IllegalArgumentException("Recipe already exists : " + r.getId());
		}
		em.persist(r);
	}
	
	public void addRating(long id,int rate) {
		Recipe r = em.find(Recipe.class, id);
		Ratings rating = new Ratings();
		r.updateRating(rating);
		em.merge(r);
	}

	public ArrayList<Triplet> getRecipesForProfil(long id){
		TypedQuery<Recipe> query = em.createQuery("SELECT r FROM Recipe r WHERE r.authorID = :authorID", Recipe.class);
		query.setParameter("authorID", id);
		List<Recipe> tmp = query.getResultList();
		ArrayList<Triplet> listToReturn = new ArrayList();
        Iterator it = tmp.iterator();
        while (it.hasNext()) {
        	Recipe r = (Recipe) it.next();
        	listToReturn.add(new Triplet(r.getId(),r.getName(),r.getIngredients()));
        }
        return listToReturn;
	}
	
	public List getRecipiesIdForProfiles(long id){
		TypedQuery<Long> query = em.createQuery("SELECT r.id FROM Recipe r WHERE r.authorID = :authorID",Long.class);
		query.setParameter("authorID", id);
		List ids = query.getResultList();
		return ids;
	}
	
	public void addComment(String comment, long id) {
		Recipe r = em.find(Recipe.class, id);
		r.addComent(comment);
		em.merge(r);
	}
	
	public void removeRecipe(long id) {
		Recipe r = em.find(Recipe.class, id);
		em.remove(r);
	}
	

	public Recipe getRecipe(long id) {
		Recipe r = em.find(Recipe.class, id);
		return r;
	}
	
		
	public Long count() {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Recipe.class)));
		return em.createQuery(cq).getSingleResult();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList <Long> getRecipeWithIngredientID(ArrayList<Long> ing_id){
		ArrayList <Long> tr = new ArrayList<Long>();
		TypedQuery<Recipe> query = em.createQuery("SELECT r FROM Recipe r", Recipe.class);
		List<Recipe> rl = query.getResultList();
		for(Recipe r: rl ) {
			Set<Ingredients> ing = r.getIngredients();
			ArrayList containedIngId = new ArrayList();
			for(Ingredients i: ing) {
				containedIngId.add(i.getId());
			}
			if(containedIngId.containsAll(ing_id)) {
				tr.add(r.getId());
			}
		}

	return tr;
	}
}
