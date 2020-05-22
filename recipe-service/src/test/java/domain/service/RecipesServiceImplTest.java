package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.javatuples.Triplet; 

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.AfterAll;
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
import domain.model.RecipeDTO;
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
		Set<Ratings> ini = r.getRatings();
		int testVal = ini.size();
		em.persist(r);
		recipesService.addRating(r.getId(), 4);
		Recipe r2 = em.find(Recipe.class, r.getId());
		Set<Ratings> fin = r2.getRatings();
		assertEquals(testVal+1,fin.size());
		
	}
	
	
	@Test
	void testGetRecipesForProfil() {
		Recipe r1 = randomRecipe();
		em.persist(r1);
		long profilID = r1.getAuthorID();
		ArrayList<RecipeDTO> recipes = recipesService.getRecipesForProfil(profilID);
		assertEquals(recipes.size(),1);
		assertEquals(recipes.get(0).getId(),r1.getId());
		assertEquals(recipes.get(0).getName(),r1.getName());
		ArrayList<Long >ing =  recipes.get(0).getIng();
		assertEquals(ing.size(),r1.getIngredients().size());

	}
	
	
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
	
	@Test 
	void testGetRecipes(){
		Recipe r = randomRecipe();
		em.persist(r);
		Recipe res = recipesService.getRecipe(r.getId());
		assertEquals(r.getRatings().size(),res.getRatings().size());
	}
	
	@Test
	void testGetRecipeWithIngredientID() {
		Recipe r = randomRecipe();
		em.persist(r);
		ArrayList<Long> ing_id = new ArrayList<Long>();		
		Recipe r2 = randomRecipe();
		em.persist(r2);
		ing_id.clear();

		for (Ingredients in: r2.getIngredients()) {
			ing_id.add(in.getIngredientID());
			break; //test with only 1 ingredients
		}
		ArrayList<RecipeDTO> res = recipesService.getRecipeWithIngredientID(ing_id);
		assertEquals(res.size(),1);
		assertEquals(r2.getId(),res.get(0).getId());
		
	}
	
	
	@Test
	public void testGetTendcies() {
		float maxMean = 0;
		long maxMeanId = 0;
		long maxMeanId2 = 0;
		for (int i= 0;  i < 250; i ++) {
			Recipe r = randomRecipe();
			em.persist(r);
			float recipeMean = 0;
			for(Ratings g: r.getRatings()) {
				recipeMean += g.getRate();
			}
			recipeMean = recipeMean / r.getRatings().size();
			
			if( maxMean <= recipeMean)
			{
				maxMeanId2 = maxMeanId;
				maxMeanId = r.getId();
				maxMean = recipeMean;
				
			}
		}
		ArrayList<RecipeDTO> ids = recipesService.getTendancies();
		assertEquals(ids.get(0).getId(),maxMeanId);
		assertEquals(ids.get(1).getId(),maxMeanId2);
	}
	
	
	@Test
	public void testGetRecipeOfTheMont() {
		int counter = 1;
		//Be sure there is at least one recipe
		Recipe rand = randomRecipe();
		DateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal1 = Calendar.getInstance();
        Date Todaydate1 = cal1.getTime();
        String todaysdate1 = dateFormat1.format(Todaydate1);
        String PartTD1[] = todaysdate1.split("/");
        String dabla = PartTD1[1]+"/"+PartTD1[0]+"/"+PartTD1[2];
        rand.setDate(dabla);
        em.persist(rand);
        
		for (int i= 0; i< 250; i++) {
			Recipe r = randomRecipe();
			int month = new Random().nextInt(12)+1;
			int year = new Random().nextInt(10)+2018;
			String date = "01/"+Integer.toString(month)+"/"+Integer.toString(year);
			r.setDate(date);
			em.persist(r);
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	        Calendar cal = Calendar.getInstance();
	        Date Todaydate = cal.getTime();
	        String todaysdate = dateFormat.format(Todaydate);
	        String PartTD[] = todaysdate.split("/");
	        if (Integer.parseInt(PartTD[0]) == month && Integer.parseInt(PartTD[2]) ==year) {
	        	counter += 1;
	        }
		}
		ArrayList<RecipeDTO> ret = recipesService.getRecipeOfTheMonth();
		assertEquals(counter,ret.size());
	}


	public Recipe randomRecipe() {
		Recipe r = new Recipe();
		
		Set<Comments> commentaire = new HashSet<Comments>();
		Comments c1 = new Comments(); c1.setComment("C etait bon");
		Comments c2 = new Comments(); c2.setComment("C etait pas bon");
		commentaire.add(c1);commentaire.add(c2);
		
		Set<Ingredients> ing = new HashSet<Ingredients>();
		Ingredients i1 = new Ingredients();
		i1.setIngredientID((long) new Random().nextInt(100 + 1)+1);
		i1.setQuantite(2);
		
		Ingredients i2 = new Ingredients(); 
		i2.setIngredientID((long)new Random().nextInt(100 + 1)+1);
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
		r.setDate("21/04/2010");
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
