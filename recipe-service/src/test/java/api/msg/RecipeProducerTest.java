package api.msg;

import domain.model.Category;
import domain.model.CategoryEnum;
import domain.model.Comments;
import domain.model.Ingredients;
import domain.model.Ratings;
import domain.model.Recipe;
import domain.model.Steps;
import domain.service.RecipeService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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


@ExtendWith(MockitoExtension.class)
class RecipeProducerTest {
	
	@Mock
	private SimpleKafkaProducer<String, ArrayList> kafkaProducer;
	@Mock
	private RecipeService rs;

	@InjectMocks
	private RecipeProducer producer;

	@Test
	void testSendRecipeAdded() {
		Recipe r = randomRecipe();
		producer.sendRecipeAdded(r);
		ArrayList ts = new ArrayList();
		ts.add(r.getAuthorID());
		ts.add(r.getId());
		verify(kafkaProducer, times(1)).send("Recipe added", ts);
		
	}



	public Recipe randomRecipe() {
		Recipe r = new Recipe();
		
		Set<Comments> commentaire = new HashSet<Comments>();
		Comments c1 = new Comments(); c1.setComment("C etait bon");
		Comments c2 = new Comments(); c2.setComment("C etait pas bon");
		commentaire.add(c1);commentaire.add(c2);
		
		Set<Ingredients> ing = new HashSet<Ingredients>();
		Ingredients i1 = new Ingredients(); i1.setId((long) 2); i1.setQuantite(2);
		Ingredients i2 = new Ingredients(); i2.setId((long) 1); i2.setQuantite(1);
		ing.add(i1); ing.add(i2);

		Set<Steps> step = new HashSet<Steps>();
		Steps s1 = new Steps(); s1.setSteps("Prechauffe le four");
		Steps s2 = new Steps(); s2.setSteps("Mets dans le four");
		step.add(s1); step.add(s2);
		
		Set<Ratings> rate = new HashSet<Ratings>();
		Ratings r1 = new Ratings(); r1.setRate(new Random().nextInt(5 + 1)+1);
		Ratings r2 = new Ratings(); r2.setRate(new Random().nextInt(5 + 1)+1);
		rate.add(r1);rate.add(r2);
		
		Set<Category> cat = new HashSet<Category>();
		Category cat1 = new Category(); cat1.setCategory(CategoryEnum.Breakfast);
		Category cat2 = new Category(); cat2.setCategory(CategoryEnum.Vegetarian);
		cat.add(cat1); cat.add(cat2);
		

		r.setAuthorID((long) new Random().nextInt(9999 + 1)+1); // Set the profilID between 1 et 10000
		r.setDate("Demain");
		r.setDifficulty(new Random().nextInt(10 + 1)+1); // Set the difficulty between 1 et 10
		r.setName("Pizza");
		r.setTime(2);
		
		r.setSteps(step);
		r.setRatings(rate);
		r.setCategory(cat);
		r.setIngredients(ing);
		r.setComments(commentaire);
		
		return r;
	}
	
	
	
}
