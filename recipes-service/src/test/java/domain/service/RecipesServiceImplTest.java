package domain.service;


import domain.model.CategoryEnum;
import domain.model.Recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.ArrayList;
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
//import eu.drus.jpa.unit.api.JpaUnit;

//@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class RecipesServiceImplTest {

	@Spy
	@PersistenceContext(unitName = "RecipesPUTest")
	EntityManager em;

	@InjectMocks
	private RecipeServiceImpl recipesService;

	
	
	
	@Test
	void testaddRecipe() {
		Recipe re= new Recipe("Pates", 1,"date",2, 2);
		System.out.println(re.getName());
		recipesService.addRecipe(re);
		System.out.println("Start test addRecipe");

		assertEquals(re.getTime(),1+1);
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
