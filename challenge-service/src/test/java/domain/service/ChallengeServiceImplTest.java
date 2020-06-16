package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
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
import domain.model.ChallengeDTO;
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
		List<Challenge> challenges = challengeService.getAll();
		int size = challenges.size();
		
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		
		assertEquals(size + 4, challengeService.getAll().size());
	}

	@Test
	void testCount() {
		List<Challenge> challenges = challengeService.getAll();
		int size = challenges.size();
		
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		
		Long count = challengeService.count();
		assertEquals(size + 4, count);
	}
	
	@Test
	void testUpdate() {
		challengeService.create(getRandomChallenge());
		Challenge challenge = challengeService.getAll().get(0);
		assertNotNull(challenge);
		Long id = challenge.getId();
		challenge.setName("Deniz");
		challengeService.update(challenge);
		challenge = challengeService.get(id);
		assertEquals("Deniz", challenge.getName());
	}
	
	
	@Test
	void testUpdateNonExistant() {
		Challenge i = new Challenge() {
			private static final long serialVersionUID = -8471786765248152721L;
			
			@Override
			public Long getId() {
				return Long.MAX_VALUE;
			}
		};
		assertThrows(IllegalArgumentException.class, () -> {
			challengeService.update(i);
		});
	}

	@Test
	void testGet() {
		challengeService.create(getRandomChallenge());
		Challenge challenge = challengeService.getAll().get(0);
		assertNotNull(challenge);
		Long id = challenge.getId();
		Challenge getChallenge = challengeService.get(id);
		assertEquals(challenge.getName(), getChallenge.getName());     
	}
	
	@Test
	void testgetChallengesForProfil() {
		Challenge ch1 = getRandomChallenge();
		em.persist(ch1);
		String profilID = ch1.getAuthorID();
		ArrayList<ChallengeDTO> challenges = challengeService.getChallengesForProfil(profilID);
		assertEquals(1,challenges.size());
	}

	@Test
	void testGetNonExistant() {
		List<Challenge> challenges = challengeService.getAll();
		System.out.println("testGetNonExistant:" + challenges.size());

		assertNull(challengeService.get(Long.MAX_VALUE));
	}

	@Test
	void testCreate() {
		Challenge challenge = getRandomChallenge();
		challengeService.create(challenge);
		assertNotNull(challenge.getId());
	}


	@Test
	void testCreateDuplicate() {
		Challenge challenge = getRandomChallenge();
		challengeService.create(challenge);
		assertThrows(IllegalArgumentException.class, () -> {
			challengeService.create(challenge);
		});
	}
	
	
	@Test
	void testAddSolution() {
		Recipe solution = new Recipe();
		solution.setRecipeId((long) 20);
		Challenge challenge = getRandomChallenge();
		Set<Recipe> oldListRecipe = challenge.getSolutions();
		Long idSolution = solution.getRecipeId();
		challengeService.create(challenge);
		Long idChallenge = challenge.getId();
		oldListRecipe.add(solution);
		challengeService.addSolution(idChallenge, idSolution);
		Challenge challengeDB = challengeService.get(idChallenge);
		Set<Recipe> newListSolution = challengeDB.getSolutions();
		assertEquals(newListSolution,oldListRecipe);		
	}
	
	@Test
	void testRemoveChallenge() {
		Challenge challenge = getRandomChallenge();
		challengeService.create(challenge);
		Long challengeId = challenge.getId();
		challengeService.removeChallenge(challengeId);
		assertThrows(IllegalArgumentException.class, () -> {
			challengeService.removeChallenge(challengeId);
		});
	}
	
	@Test
	void testGetChallengeFromIngIds() {
		Challenge c= getRandomChallenge();
		em.persist(c);
		ArrayList<Long> ing_id = new ArrayList<Long>();		
		Challenge c2= getRandomChallenge();
		em.persist(c2);
		ing_id.clear();

		for (Ingredient in: c2.getIngredients()) {
			ing_id.add(in.getIngredientId());
			//break; //test with only 1 ingredients
		}
		ArrayList<ChallengeDTO> res = challengeService. getChallengesFromIngredientsIds(ing_id);
		assertEquals(1,res.size());
		//assertEquals(c2.getId(),res.get(0).);
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
		c.setAuthorID(UUID.randomUUID().toString());
		c.setIngredients(Ingredients);
		c.setSolutions(Solutions);

		return c;
	}
}
