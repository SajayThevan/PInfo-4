package api.msg;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
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
import domain.model.Profile;
import domain.model.RecipeFav;

@ExtendWith(MockitoExtension.class)
class ProfileProducerTest {

	@Mock
	private SimpleKafkaProducer<String, String> kafkaProducer;

	@InjectMocks
	private ProfileProducer producer;

	@Test
	void testSendProfileDeleted() {
		Profile profile = getRandomProfile();
		profile.setId("asdafs");
		String profileId = profile.getId();
		producer.sendProfileDeleted(profileId);
		verify(kafkaProducer, times(1)).send("profilDelete", profileId);
		
	}
	
	private Profile getRandomProfile() {
		Profile p = new Profile();
		Random rand = new Random();
		
		Ingredient ing = new Ingredient();
		ing.setIngredientId(((long) rand.nextInt(100)));
		ing.setQuantity(rand.nextInt(100));
		
		
		Ingredient ing2 = new Ingredient();
		ing2.setIngredientId(((long) rand.nextInt(100)));
		ing2.setQuantity(rand.nextInt(100));
		
		Ingredient ing3 = new Ingredient();
		ing3.setIngredientId(((long) rand.nextInt(100)));
		ing3.setQuantity(rand.nextInt(100));
		
		Set<Ingredient> Fridge = new HashSet<Ingredient>();
		Fridge.add(ing);
		Fridge.add(ing2);
		Fridge.add(ing3);
		
		RecipeFav re = new RecipeFav();
		re.setRecipeId((long) 4);
	
		RecipeFav re2 = new RecipeFav();
		re2.setRecipeId((long) 12);
		
		RecipeFav re3 = new RecipeFav();
		re3.setRecipeId((long) 5);

		Set<RecipeFav> Favoris = new HashSet<RecipeFav>();
		Favoris.add(re);
		Favoris.add(re2);
		Favoris.add(re3);
		
        
		p.setPseudo(UUID.randomUUID().toString());
		p.setEmail(UUID.randomUUID().toString());
		p.setFirstName(UUID.randomUUID().toString());
		p.setLastName(UUID.randomUUID().toString());
		p.setScore(rand.nextInt(100));
		p.setFridgeContents(Fridge);
		p.setFavouriteRecipes(Favoris);
		return p;	
	}

	
}
