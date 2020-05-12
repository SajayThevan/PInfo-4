package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.javatuples.Triplet; 

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.CategoryEnum;
import domain.model.Ratings;
import domain.model.Steps;
import domain.model.Ingredients;
import domain.model.Comments;
import domain.model.Category;
import domain.model.Recipe;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class RecipesServiceImplTest {

	@Spy
	@PersistenceContext(unitName = "RecipePUTest")
	EntityManager em;

	@InjectMocks
	private RecipeServiceImpl recipesService;
	
	@SuppressWarnings("rawtypes")
	@Test
	void testAddRecipe() {
		Recipe r1 = randomRecipe();
		Recipe r2 = randomRecipe();
		recipesService.addRecipe(r1);
		recipesService.addRecipe(r2);
		List res = em.createNativeQuery("select name from Recipe").getResultList();
		assertEquals(res.size(),2);
		
	}

	
	@Test
	void testAddRating() {
		Recipe r = randomRecipe();
		Set<Ratings> ini = r.getRatings();
		int testVal = ini.size();
		em.persist(r);
		recipesService.addRating(r.getId(), 4);
		Recipe r2 = em.find(Recipe.class, r.getId());
		Set<Ratings> fin = r2.getRatings();
		assertEquals(testVal+1,fin.size());
		
	}
	
	
	@SuppressWarnings("rawtypes")
	@Test
	void testGetRecipesForProfil() {
		Recipe r1 = randomRecipe();
		em.persist(r1);
		long profilID = r1.getAuthorID();
		ArrayList<Triplet> recipes = recipesService.getRecipesForProfil(profilID);
		assertEquals(recipes.size(),1);
		assertEquals(recipes.get(0).getValue(0),r1.getId());
		assertEquals(recipes.get(0).getValue(1),r1.getName());
		Set<Ingredients> ing = (Set<Ingredients>) recipes.get(0).getValue(2);
		assertEquals(ing.size(),r1.getIngredients().size());

	}
	
	
	@SuppressWarnings("rawtypes")
	@Test
	void testGetRecipiesIDForProfiles() {
		Recipe r = randomRecipe();
		List res = recipesService.getRecipiesIdForProfiles(r.getAuthorID()); // Get the inital number of element in DB
		em.persist(r);
		List a = recipesService.getRecipiesIdForProfiles(r.getAuthorID());
		assertEquals(a.size(),res.size()+1);		
	}
	
	@Test
	void testAddComment() {
		Recipe r = randomRecipe();
		em.persist(r);
		int ini=r.getComments().size();
		recipesService.addComment("C'était bon",r.getId());
		int fin = r.getComments().size();
		assertEquals(ini+1,fin);
	}
	
	@Test
	void testRemoveRecipe() {
		Recipe r = randomRecipe();
		int ini =  em.createNativeQuery("select name from Recipe").getResultList().size();
		em.persist(r);
		int mid =  em.createNativeQuery("select name from Recipe").getResultList().size();
		assertEquals(ini+1,mid); // To be sure persist works
		recipesService.removeRecipe(r.getId());
		int fin =  em.createNativeQuery("select name from Recipe").getResultList().size();
		assertEquals(ini,fin);
	}
	
	//TODO: Remake this test
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Test 
//	void testGetRecipes(){
//		Recipe r = randomRecipe();
//		em.persist(r);
//		ArrayList res = recipesService.getRecipe(r.getId());	
//		assertEquals(r.getAuthorID(),res.get(2)); 
//		
//		Set<Comments>  c = r.getComments();
//		Set<Comments> cf = (Set<Comments>)res.get(10);
//		assertEquals(cf.size(),c.size());
//		
//		Set<Category>  cat = r.getCategory();
//		Set<Category> catRes = (Set<Category>)res.get(6);
//		assertEquals(cat.size(),catRes.size());
//		
//		Set<Ratings>  rates = r.getRatings();
//		Set<Ratings> ratesRes = (Set<Ratings>)res.get(9);
//		assertEquals(rates.size(),ratesRes.size());
//		
//	}
	
	
	
	public Recipe randomRecipe() {
		Recipe r = new Recipe();
		
		Set<Comments> commentaire = new HashSet<Comments>();
		Comments c1 = new Comments(); c1.setComment("C etait bon");
		Comments c2 = new Comments(); c2.setComment("C etait pas bon");
		commentaire.add(c1);commentaire.add(c2);
		
		Set<Ingredients> ing = new HashSet<Ingredients>();
		Ingredients i1 = new Ingredients();
		i1.setIngredientID((long) 2);
		i1.setQuantite(2);
		
		Ingredients i2 = new Ingredients(); 
		i2.setIngredientID((long) 1);
		i2.setQuantite(1);
		
		ing.add(i1); 
		ing.add(i2);

		Set<Steps> step = new HashSet<Steps>();
		Steps s1 = new Steps(); s1.setSteps("Prechauffe le four");
		Steps s2 = new Steps(); s2.setSteps("Mets dans le four");
		step.add(s1); step.add(s2);
		
		Set<Ratings> rate = new HashSet<Ratings>();
		Ratings r1 = new Ratings(); r1.setRate(new Random().nextInt(5 + 1)+1);
		Ratings r2 = new Ratings(); r2.setRate(new Random().nextInt(5 + 1)+1);
		rate.add(r1);rate.add(r2);
		
		Set<Category> cat = new HashSet<Category>();
		Category cat1 = new Category(); cat1.setCategory(CategoryEnum.Breakfast);
		Category cat2 = new Category(); cat2.setCategory(CategoryEnum.Vegetarian);
		cat.add(cat1); cat.add(cat2);
		

		r.setAuthorID((long) new Random().nextInt(9999 + 1)+1);  //Set the profilID between 1 et 10000
		r.setDate("Demain");
		r.setDifficulty(new Random().nextInt(10 + 1)+1); // Set the difficulty between 1 et 10
		r.setName("Pizza");
		r.setTime(2);
		
		r.setSteps(step);
		r.setRatings(rate);
		r.setCategory(cat);
		r.setComments(commentaire);
		r.setIngredients(ing);
		
		return r;
	}

}
