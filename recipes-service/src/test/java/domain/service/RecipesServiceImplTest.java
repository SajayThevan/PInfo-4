package domain.service;

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
	void testDeniz() {
		Recipe r = new Recipe();
		List<String> co = new ArrayList<String>();
		co.add("TG");
		List<Long> ing = new ArrayList<Long>();
		ing.add((long)2);
		List<String> step = new ArrayList<String>();
		step.add("Tu mets dans le four et tg");
		List<Integer> rating = new ArrayList<Integer>();
		rating.add(2);
		List cat = new ArrayList<CategoryEnum>();
		cat.add(CategoryEnum.Dinner);
		r.setAuthorID((long) 1);
		r.setDate("Demain");
		r.setDifficulty(2);
		r.setName("Pizza");
		r.setTime(2);
		r.setSteps(step);
		r.setRatings(rating);
		r.setCategory(cat);
		r.setIngredients(ing);
		r.setComments(co);
		em.persist(r);
		List reDB = em.createQuery("SELECT name from Recipe").getResultList();
		System.out.println("==================================================");
		System.out.println(reDB.size());
		List a = recipesService.getRecipiesIdForProfiles(r.getAuthorID());
		assertEquals(a.size(),1);
		
		
	}

//	@Test
//	void testDatabase() {
//		em.createNativeQuery("INSERT INTO recipe ( authorID, date, difficulty, name, time) VALUES ( 1, 'demain',2,'choux fleur',1);").executeUpdate();
//		List reDB = em.createQuery("SELECT name from Recipe").getResultList();
//		assertEquals(reDB.size(),reDB.size()+1);
//	}
	
}
