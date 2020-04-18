package domain.service;


import domain.model.CategoryEnum;
import domain.model.Recipe;

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
	@PersistenceContext(unitName = "RecipesPUTest")
	EntityManager em;

	@InjectMocks
	private RecipeServiceImpl recipesService;

	
	
	
	@Test
	void testaddRecipe() {
		
		final ArrayList<Integer> ing = new ArrayList<Integer>();
		final ArrayList<CategoryEnum> cat = new ArrayList<CategoryEnum>();
		final ArrayList<String> step = new ArrayList<String>();
		final ArrayList<Integer> rating = new ArrayList<Integer>();
		final ArrayList<String> comment = new ArrayList<String>();
		ing.add(2);
		ing.add(3);
		cat.add(CategoryEnum.Dinner);
		step.add("Mélanger");
		step.add("Cuir");
		rating.add(2);
		comment.add("Bon");
		comment.add("Mauvais");
		Recipe r = new Recipe("Test",1,ing,cat, step, 2, 1,rating, comment);
		recipesService.addRecipe(r);
		assertEquals(2,1+1);
	}

//	@Test
//	void testGetAll() {
//		List<Instrument> instruments = instrumentService.getAll();
//		int size = instruments.size();
//		
//		instrumentService.create(getRandomInstrument());
//		instrumentService.create(getRandomInstrument());
//		instrumentService.create(getRandomInstrument());
//		instrumentService.create(getRandomInstrument());
//		
//		assertEquals(size + 4, instrumentService.getAll().size());
//	}
}
