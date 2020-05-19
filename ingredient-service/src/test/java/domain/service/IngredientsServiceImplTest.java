package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Ingredient;
import eu.drus.jpa.unit.api.JpaUnit;

//import domain.service.IngredientServiceImpl;  

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class IngredientsServiceImplTest {

	@Spy
	@PersistenceContext(unitName = "IngredientPUTest")
	EntityManager em;

	@InjectMocks
	private IngredientServiceImpl ingredientsService;
	
	@Test
	void testCreate() {
		System.out.println("--- testCreate : ---");
		Ingredient ingredientTest = getRandomIngredient();
		ingredientsService.create(ingredientTest);
		assertNotNull(ingredientTest.getId());
	}
	
	@Test
	void testGet() {
		System.out.println("--- testGet : ---");
		ingredientsService.create(getRandomIngredient());
		Ingredient ingredient = ingredientsService.getAll().get(0);
		assertNotNull(ingredient);
		Long id = ingredient.getId();
		System.out.println("testGet id: " + ingredient);
		Ingredient getIngredient = ingredientsService.get(id);
		assertEquals(ingredient.getId(), getIngredient.getId());
	}
	
	@Test
	void testGetAll() {
		List<Ingredient> ingredients = ingredientsService.getAll();
		int size = ingredients.size();
		
		ingredientsService.create(getRandomIngredient()); // tests create at the same time 
		ingredientsService.create(getRandomIngredient1());
		ingredientsService.create(getRandomIngredient2());
		ingredientsService.create(getRandomIngredient3());
		
		assertEquals(size + 4, ingredientsService.getAll().size());
	}
	
	@Test
	void testGetAllNames() {
		List<Ingredient> ingredients = ingredientsService.getAll();
		int size = ingredients.size();
		
		ingredientsService.create(getRandomIngredient()); // tests create at the same time 
		ingredientsService.create(getRandomIngredient1());
		ingredientsService.create(getRandomIngredient2());
		ingredientsService.create(getRandomIngredient3());
		System.out.println("--- get all pairs for research : ---");
		System.out.println(ingredientsService.getAllNames());
		assertEquals(size + 4, ingredientsService.getAllNames().size());
	}

	@Test
	void testComputeCalories() {
		System.out.println("--- testcomputeCalories : ---");
		ingredientsService.create(getRandomIngredient());
		ingredientsService.create(getRandomIngredient1());
		Ingredient ingredient1 = ingredientsService.getAll().get(0);
		assertNotNull(ingredient1);
		Ingredient ingredient2 = ingredientsService.getAll().get(1);
		assertNotNull(ingredient2);
		
		System.out.println("test calories, show ingredients : ");
		System.out.println(ingredient1);
		System.out.println(ingredient2);
		
		double getKcalTotal = ingredient1.getKcal() + ingredient2.getKcal();
		List<Long> listID = new ArrayList<>();
		listID.add(ingredient1.getId());
		listID.add(ingredient2.getId());
		
		System.out.println(listID);
		System.out.println(getKcalTotal);
		
		double testComputeCalories = ingredientsService.computeCalories(listID);
		//assertEquals(getKcalTotal, 11)
		//assertEquals(testComputeCalories, 11);
		assertEquals(getKcalTotal, testComputeCalories); 
	}
	
	@Test
	void testGetPossibleIngredients() {
		System.out.println("--- testGetPossibleIngredients : ---");
		ingredientsService.create(getRandomIngredient1());
		ingredientsService.create(getRandomIngredient2());
		ingredientsService.create(getRandomIngredient3());
		Ingredient ingredient1 = ingredientsService.getAll().get(0);
		assertNotNull(ingredient1);
		Ingredient ingredient2 = ingredientsService.getAll().get(1);
		assertNotNull(ingredient2);
		Ingredient ingredient3 = ingredientsService.getAll().get(2);
		assertNotNull(ingredient3);
		
		System.out.println("TEST GET SEVERAL INGREDIENTS" );
		System.out.println(ingredient1);
		System.out.println(ingredient2);
		System.out.println(ingredient3);
		
		// possibilities working : 
		List<Object> possibilities = new ArrayList<>(); 
		List<Object> possibleIng1 = new ArrayList<>(); 
		possibleIng1.add(ingredient1.getId());
		possibleIng1.add(ingredient1.getName());
		possibilities.add(possibleIng1);
		List<Object> possibleIng2 = new ArrayList<>(); 
		possibleIng2.add(ingredient2.getId());
		possibleIng2.add(ingredient2.getName());
		possibilities.add(possibleIng2);
		
		List<Object> possibleIngredients = ingredientsService.getPossibleIngredients("choco");
		System.out.println("retour de possibilites : ");
		System.out.println(possibleIngredients);
		assertEquals(possibilities, possibleIngredients); 
	}
	
	@Test
	void testGetNonExistant() {
		System.out.println("--- testGetNonExistant : ---");
		List<Ingredient> ingredients = ingredientsService.getAll();
		System.out.println("testGetNonExistant:" + ingredients.size());

		assertNull(ingredientsService.get(Long.MAX_VALUE));
	}
	
	@Test
	void testCount() {
		System.out.println("-----------------DEBUT TEST COUNT-----------------");
		List<Ingredient> ingredients = ingredientsService.getAll();
		int size = ingredients.size();
		
		ingredientsService.create(getRandomIngredient());
		ingredientsService.create(getRandomIngredient());
		ingredientsService.create(getRandomIngredient());
		ingredientsService.create(getRandomIngredient());
		
		Long count = ingredientsService.count();
		assertEquals(size + 4, count);
		System.out.println("-----------------TEST COUNT TERMINE-----------------");
	}
	
	@Test
	private Ingredient getRandomIngredient() {
		Ingredient ing = new Ingredient();
		//ing.setId((long) 10);
		//ing.setName(UUID.randomUUID().toString());
		ing.setName("ingredient test0");
		ing.setKcal(8);
		//ing.setFat((int) Math.round(Math.random()*1000));
		ing.setFat(3);
		ing.setCholesterol(4);
		ing.setProtein(5);
		ing.setCholesterol(6);
		ing.setSalt(7);
		return ing;
	}
	
	@Test
	private Ingredient getRandomIngredient1() {
		Ingredient ing = new Ingredient();
		//ing.setName(UUID.randomUUID().toString());
		//ing.setId((long) 2);
		ing.setName("chocolat au lait");
		ing.setKcal(3.1);
		//ing.setFat((int) Math.round(Math.random()*1000));
		ing.setFat(3);
		ing.setCholesterol(4);
		ing.setProtein(5);
		ing.setCholesterol(6);
		ing.setSalt(7);
		return ing;
	}
	
	@Test
	private Ingredient getRandomIngredient2() {
		Ingredient ing = new Ingredient();
		//ing.setName(UUID.randomUUID().toString());
		ing.setName("chocolat");
		//ing.setId((long) 3);
		ing.setKcal(8);
		//ing.setFat((int) Math.round(Math.random()*1000));
		ing.setFat(3);
		ing.setCholesterol(4);
		ing.setProtein(5);
		ing.setCholesterol(6);
		ing.setSalt(7);
		return ing;
	}
	
	@Test
	private Ingredient getRandomIngredient3() {
		Ingredient ing = new Ingredient();
		//ing.setName(UUID.randomUUID().toString());
		ing.setName("nothing");
		//ing.setId((long) 4);
		ing.setKcal(8);
		//ing.setFat((int) Math.round(Math.random()*1000));
		ing.setFat(3);
		ing.setCholesterol(4);
		ing.setProtein(5);
		ing.setCholesterol(6);
		ing.setSalt(7);
		return ing;
	}
	
}