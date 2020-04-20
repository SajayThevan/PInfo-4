package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.javatuples.Triplet; 

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.CategoryEnum;
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
		List<Integer> ini = r.getRatings();
		int testVal = ini.size();
		em.persist(r);
		recipesService.addRating(r.getId(), 3);
		Recipe r2 = em.find(Recipe.class, r.getId());
		List<Integer> fin = r2.getRatings();
		assertEquals(testVal+1,fin.size());
		
	}
	
	@Test
	void testGetRecipesForProfil() {
		Recipe r1 = randomRecipe();
		em.persist(r1);
		long profilID = r1.getAuthorID();
		ArrayList<Triplet> recipes = recipesService.getRecipesForProfil(profilID);
		assertEquals(recipes.size(),1);
		assertEquals(recipes.get(0).getValue(0),r1.getId());
		assertEquals(recipes.get(0).getValue(1),r1.getName());
		assertEquals(recipes.get(0).getValue(2),r1.getIngredients());
	}
	
	
	@SuppressWarnings("rawtypes")
	@Test
	void testGetRecipiesIDForProfiles() {
		List res = em.createNativeQuery("select name from Recipe").getResultList(); // Get the inital number of element in DB
		Recipe r = randomRecipe();
		em.persist(r);
		List a = recipesService.getRecipiesIdForProfiles(r.getAuthorID());
		assertEquals(a.size(),res.size());		
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
	
	@Test 
	void testGetRecipes(){
		Recipe r = randomRecipe();
		em.persist(r);
		ArrayList res = recipesService.getRecipe(r.getId());
		assertEquals(r.getAuthorID(),res.get(2));
	}
	
	
	
	public Recipe randomRecipe() {
		Recipe r = new Recipe();
		List<String> co = new ArrayList<String>();
		co.add("TG");
		List<Long> ing = new ArrayList<Long>();
		ing.add((long)2);
		List<String> step = new ArrayList<String>();
		step.add("Tu mets dans le four et tg");
		List<Integer> rating = new ArrayList<Integer>();
		rating.add(5);
		List cat = new ArrayList<CategoryEnum>();
		cat.add(CategoryEnum.Dinner);
		r.setAuthorID((long) new Random().nextInt(9999 + 1)+1); // Set the profilID between 1 et 10000
		r.setDate("Demain");
		r.setDifficulty(new Random().nextInt(10 + 1)+1); // Set the difficulty between 1 et 10
		r.setName("Pizza");
		r.setTime(2);
		r.setSteps(step);
		r.setRatings(rating);
		r.setCategory(cat);
		r.setIngredients(ing);
		r.setComments(co);
		
		return r;
	}

}
