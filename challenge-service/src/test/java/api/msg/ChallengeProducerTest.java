package api.msg;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Ingredient;
import domain.model.Challenge;
import domain.model.Recipe;
import domain.service.ChallengeService;

@ExtendWith(MockitoExtension.class)
class ChallengeProducerTest {

	@Mock
	private SimpleKafkaProducer<String, Challenge> kafkaProducer;
	@Mock
	private ChallengeService challengeService;

	@InjectMocks
	private ChallengeProducer producer;

	@Test
	void testSendAllChallenges() {
		List<Challenge> challenges = getRandomChallengeCollection();
		when(challengeService.getAll()).thenReturn(challenges);
		producer.sendAllChallenges();
		verify(kafkaProducer, times(challenges.size())).send(eq("challenges"), any(Challenge.class));
	}

	@Test
	void testSendChallenge() {
		Challenge challenge = getRandomChallenge();
		producer.send(challenge);
		verify(kafkaProducer, times(1)).send("challenges", challenge);
	}

	@Test
	void testSendLong() {
		Challenge challenge = getRandomChallenge();
		when(challengeService.get(challenge.getId())).thenReturn(challenge);
		producer.send(challenge.getId());
		verify(kafkaProducer, times(1)).send("challenges", challenge);
	}

	@Test
	void testSendLongNull() {
		Challenge challenge = getRandomChallenge();
		when(challengeService.get(challenge.getId())).thenReturn(null);
		producer.send(challenge.getId());
		verify(kafkaProducer, times(0)).send("challenges", challenge);
	}

	private List<Challenge> getRandomChallengeCollection() {
		List<Challenge> challenges = new ArrayList<>();
		long numberOfChallenge = Math.round((Math.random() * 1000));
		for (int i = 0; i < numberOfChallenge; i++) {
			challenges.add(getRandomChallenge());
		}
		return challenges;
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
