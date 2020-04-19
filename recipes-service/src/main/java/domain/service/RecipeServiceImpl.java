package domain.service;


import java.lang.reflect.Array;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.javatuples.Triplet; 
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.constraints.NotNull;

import domain.model.CategoryEnum;
import domain.model.Recipe;
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
	
	
	//TODO: Implement that shit
	
	public void addRecipe(Recipe r) {
		if (r.getId() != null) {
			throw new IllegalArgumentException("Instrument already exists : " + r.getId());
		}
		em.persist(r);
	}
	
	public void addRating(int id,int rate) {
		Recipe r = em.find(Recipe.class, id);
		r.updateRating(rate);
		em.merge(r);
	}

	public ArrayList<Triplet> getRecipesForProfil(int id){
		//TODO: Update when decidie how to store array
		
		List<Recipe> tmp = em.createQuery("SELECT * from Recipe where author = id").getResultList();
		ArrayList<Triplet> listToReturn = new ArrayList();
        Iterator it = tmp.iterator();
        while (it.hasNext()) {
        	Recipe r = (Recipe) it.next();
        	listToReturn.add(new Triplet(r.getId(),r.getName(),r.getIngredients()));
        }
        return listToReturn;
	}
	
	public List getRecipiesIdForProfiles(int id){
		List ids = em.createQuery("SELECT id from Recipe where author = id").getResultList();
		return ids;
	}
	
	public void addComment(String comment, int id) {
		Recipe r = em.find(Recipe.class, id);
		r.addComent(comment);
		em.merge(r);
	}
	
	public void removeRecipe(int id) {
		Recipe r = em.find(Recipe.class, id);
		em.detach(r);
	}
	
	public ArrayList getRecipe(int id) {
		Recipe r = em.find(Recipe.class, id);
		ArrayList rl = new ArrayList();
		rl.add(r.getName());
		rl.add(r.getDate());
		rl.add(r.getAuthorID());
		rl.add(r.getDifficulty());
		rl.add(r.getTime());
		//TODO: Finish and be sure to pass an ArrayList r.get
		return rl;
	}
	
	
}
