package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
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
	/*
	@Test
	void testGet() {
		System.out.println("--- testGet : ---");
		ingredientsService.create(getRandomIngredient());
		Ingredient ingredient = ingredientsService.getAll().get(0);
		assertNotNull(ingredient);
		Long id = ingredient.getId();
		Ingredient getIngredient = ingredientsService.get(id);
		assertEquals(ingredient.getName(), getIngredient.getName());
	}
	*/
	@Test
	void testGet() {
		System.out.println("--- testGet : ---");
		Long id = (long) 1;
		Ingredient ing = ingredientsService.get(id);
		System.out.println("YOUHOU ICI ");
		System.out.println("Ingredient = " + ing.getName());
	}


	@Test
	void testGetNonExistant() {
		System.out.println("--- testGetNonExistant : ---");
		List<Ingredient> ingredients = ingredientsService.getAll();
		System.out.println("testGetNonExistant:" + ingredients.size());

		assertNull(ingredientsService.get(Long.MAX_VALUE));
	}
	
	@Test
	void testCreate() {
		System.out.println("--- testCreate : ---");
		// RECRIRE LE GET!! 
		Ingredient ingredientTest = getRandomIngredient();
		ingredientsService.create(ingredientTest);
		assertNotNull(ingredientTest.getId());
	}
	
	@Test
	private Ingredient getRandomIngredient() {
		Ingredient ing = new Ingredient();
		//ing.setName(UUID.randomUUID().toString());
		ing.setName("test hello");
		ing.setKcal((int) Math.round(Math.random()*1000));
		//ing.setFat((int) Math.round(Math.random()*1000));
		ing.setFat(3);
		ing.setCholesterol((int) Math.round(Math.random()*1000));
		ing.setProtein((int) Math.round(Math.random()*1000));
		ing.setCholesterol((int) Math.round(Math.random()*1000));
		ing.setSalt((int) Math.round(Math.random()*1000));
		/*
		b.setMaturityDate(new Date());
		b.setIsin(UUID.randomUUID().toString());
		b.setQuantity(Long.valueOf(Math.round(Math.random()*1000)));
		*/
		return ing;
	}
	
}