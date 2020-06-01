package api.msg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.CategoryEnum;
import domain.model.Recipe;

@ExtendWith(MockitoExtension.class)
class RecipeConsumerTest {
	
	
	@Mock
	private RecipeProducer producer;
	
	@InjectMocks
	private RecipeConsumer consumer;
	
	//TODO: Find a way to test that shit
	
	/*@Test
	void testDeleteRecipe() {
		Recipe r = randomRecipe();
		int t = consumer.deleteRecipe(r.getId());
		assertEquals(t,1);
		
	}
	
	@Test
	void testChallengeAccepted() {
		Recipe r = randomRecipe();
		consumer.challengeAccepted(r);
	}
	/*

	public Recipe randomRecipe() {
		Recipe r = new Recipe();
		List<String> co = new ArrayList<String>();
		co.add("TG");
		List<Long> ing = new ArrayList<Long>();
		ing.add((long)2);
		List<String> step = new ArrayList<String>();
		step.add("Tu mets dans le four et tg");
		List<Integer> rating = new ArrayList<Integer>();
		rating.add(5);
		List cat = new ArrayList<CategoryEnum>();
		cat.add(CategoryEnum.Dinner);
		r.setAuthorID((long) new Random().nextInt(9999 + 1)+1); // Set the profilID between 1 et 10000
		r.setDate("Demain");
		r.setDifficulty(new Random().nextInt(10 + 1)+1); // Set the difficulty between 1 et 10
		r.setName("Pizza");
		r.setTime(2);
		r.setSteps(step);
		r.setRatings(rating);
		r.setCategory(cat);
		r.setIngredients(ing);
		r.setComments(co);
		r.setImagePath(String.valueOf( new Random().nextInt(9999 + 1)+1));
		
		return r;
	}
	
	/*
	 
	 ********************* Keep in here for test example ***********************
	 

	
	@Test
	void testUpdateAllInstrument() {
		consumer.updateInstrument("all");
		verify(producer, times(1)).sendAllInstruments();
	}
	
	@Test
	void testUpdateUnexpectedMessage() {
		assertThrows(IllegalArgumentException.class,
				() -> consumer.updateInstrument("XXX"));
	}
		*/
}
