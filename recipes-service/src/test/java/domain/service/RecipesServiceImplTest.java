package domain.service;


import domain.model.CategoryEnum;
import domain.model.Recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.ArrayList;
import java.util.List;
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
	void testDatabase() {
		em.createNativeQuery("INSERT INTO Recipe (id,authorID,date,difficulty,name,time) VALUES (1,100,'demain',2,'pizza',2)").executeUpdate();
		List reDB = em.createQuery("SELECT name from Recipe").getResultList();
		assertEquals(reDB.size(),1);
	}
	
	
	@Test
	void testaddRecipe() {
		Recipe re= new Recipe("Pates", 1,"date",2, 2);
		System.out.println(re.getName());
		recipesService.addRecipe(re);
		System.out.println("Start test addRecipe");

		assertEquals(re.getTime(),1+1);
	}


}
