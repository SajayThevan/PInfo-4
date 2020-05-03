package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Challenge;
import domain.model.Ingredient;
import domain.model.Recipe;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)

class ChallengeServiceImplTest {

	@Spy
	@PersistenceContext(unitName = "ChallengePUTest")
	EntityManager em;

	@InjectMocks
	private ChallengeServiceImpl challengeService;

	void testGetAll() {
		System.out.println("-----------------DEBUT TEST GETALL-----------------");
		List<Challenge> challenges = challengeService.getAll();
		int size = challenges.size();
		
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		
		assertEquals(size + 4, challengeService.getAll().size());
		System.out.println("-----------------TEST GETALL TERMINE-----------------");
	}

	@Test
	void testCount() {
		System.out.println("-----------------DEBUT TEST COUNT-----------------");
		List<Challenge> challenges = challengeService.getAll();
		int size = challenges.size();
		
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		
		Long count = challengeService.count();
		assertEquals(size + 4, count);
		System.out.println("-----------------TEST COUNT TERMINE-----------------");
	}
	
	@Test
	void testUpdate() {
		System.out.println("-----------------DEBUT TEST UPDATE-----------------");
		challengeService.create(getRandomChallenge());
		Challenge challenge = challengeService.getAll().get(0);
		assertNotNull(challenge);
		Long id = challenge.getId();
		challenge.setName("Deniz");
		challengeService.update(challenge);
		challenge = challengeService.get(id);
		assertEquals("Deniz", challenge.getName());
		System.out.println("-----------------TEST UPDATE TERMINE-----------------");
	}
	
	@SuppressWarnings("serial")
	@Test
	void testUpdateNonExistant() {
		System.out.println("-----------------DEBUT TEST UPDATE NON EXISTANT-----------------");
		Challenge i = new Challenge() {
			@Override
			public Long getId() {
				return Long.MAX_VALUE;
			}
		};
		assertThrows(IllegalArgumentException.class, () -> {
			challengeService.update(i);
		});
		System.out.println("-----------------TEST UPDATE NON EXISTANT TERMINE-----------------");
	}

	@Test
	void testGet() {
		System.out.println("-----------------DEBUT TEST GET-----------------");
		challengeService.create(getRandomChallenge());
		Challenge challenge = challengeService.getAll().get(0);
		assertNotNull(challenge);
		Long id = challenge.getId();
		Challenge getChallenge = challengeService.get(id);
		assertEquals(challenge.getName(), getChallenge.getName());     
		System.out.println("-----------------TEST GET TERMINE-----------------");
	}

	@Test
	void testGetNonExistant() {
		System.out.println("-----------------DEBUT TEST GET NON EXISTANT-----------------");
		List<Challenge> challenges = challengeService.getAll();
		System.out.println("testGetNonExistant:" + challenges.size());

		assertNull(challengeService.get(Long.MAX_VALUE));
		System.out.println("-----------------TEST GET NON EXISTANT TERMINE-----------------");
	}

	@Test
	void testCreate() {
		System.out.println("-----------------DEBUT TEST CREATION CHALLENGE-----------------");
		Challenge challenge = getRandomChallenge();
		challengeService.create(challenge);
		assertNotNull(challenge.getId());
		System.out.println("-----------------TEST CREATION CHALLENGE TERMINE-----------------");
	}


	@Test
	void testCreateDuplicate() {
		System.out.println("-----------------DEBUT TEST CREATION DUPLICAT-----------------");
		Challenge challenge = getRandomChallenge();
		challengeService.create(challenge);
		assertThrows(IllegalArgumentException.class, () -> {
			challengeService.create(challenge);
		});
		System.out.println("-----------------TEST CREATION DUPLICAT TERMINE-----------------");
	}

	private Challenge getRandomChallenge() {
		Challenge c = new Challenge();
		Random rand = new Random();
		
		Ingredient ing = new Ingredient();
		ing.setIngredientId(((long) rand.nextInt(100)));
		ing.setQuantity(rand.nextInt(100));
		
		
		Ingredient ing2 = new Ingredient();
		ing2.setIngredientId(((long) rand.nextInt(100)));
		ing2.setQuantity(rand.nextInt(100));;
		
		Ingredient ing3 = new Ingredient();
		ing3.setIngredientId(((long) rand.nextInt(100)));
		ing3.setQuantity(rand.nextInt(100));
		
		Set<Ingredient> Ingredients = new HashSet<Ingredient>();
		Ingredients.add(ing);
		Ingredients.add(ing2);
		Ingredients.add(ing3);
		
		Recipe re = new Recipe();
		re.setRecipeId((long) 4);
	
		Recipe re2 = new Recipe();
		re2.setRecipeId((long) 12);
		
		Recipe re3 = new Recipe();
		re3.setRecipeId((long) 5);

		Set<Recipe> Solutions = new HashSet<Recipe>();
		Solutions.add(re);
		Solutions.add(re2);
		Solutions.add(re3);
		
		c.setName(UUID.randomUUID().toString());
		c.setAuthorID((long) rand.nextInt(100));
		c.setIngredients(Ingredients);
		c.setSolutions(Solutions);

		return c;
	}
}
