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

import domain.model.Ingrediant;
import domain.model.Profile;
import domain.model.RecetteFav;
import domain.service.ProfileService;

@ExtendWith(MockitoExtension.class)
class ProfileProducerTest {

	@Mock
	private SimpleKafkaProducer<String, Profile> kafkaProducer;
	@Mock
	private ProfileService profileService;

	@InjectMocks
	private ProfileProducer producer;

	@Test
	void testSendAllProfiles() {
		System.out.println("-----------------DEBUT TEST PRODUCER SendAllProfiles-----------------");
		List<Profile> profiles = getRandomProfileCollection();
		when(profileService.getAll()).thenReturn(profiles);
		producer.sendAllProfiles();
		verify(kafkaProducer, times(profiles.size())).send(eq("profiles"), any(Profile.class));
		System.out.println("-----------------FIN TEST PRODUCER SendAllProfiles-----------------");
	}

	@Test
	void testSendProfile() {
		System.out.println("-----------------DEBUT TEST PRODUCER SendProfile-----------------");
		Profile profile = getRandomProfile();
		producer.send(profile);
		verify(kafkaProducer, times(1)).send("profiles", profile);
		System.out.println("-----------------FIN TEST PRODUCER SendProfile-----------------");
	}

	@Test
	void testSendLong() {
		System.out.println("-----------------DEBUT TEST PRODUCER SendLong-----------------");
		Profile profile = getRandomProfile();
		when(profileService.get(profile.getId())).thenReturn(profile);
		producer.send(profile.getId());
		verify(kafkaProducer, times(1)).send("profiles", profile);
		System.out.println("-----------------FIN TEST PRODUCER SendLong-----------------");
	}

	@Test
	void testSendLongNull() {
		System.out.println("-----------------DEBUT TEST PRODUCER SendLongNull-----------------");
		Profile profile = getRandomProfile();
		when(profileService.get(profile.getId())).thenReturn(null);
		producer.send(profile.getId());
		verify(kafkaProducer, times(0)).send("profiles", profile);
		System.out.println("-----------------FIN TEST PRODUCER SendLongNull-----------------");
	}

	private List<Profile> getRandomProfileCollection() {
		List<Profile> profiles = new ArrayList<>();
		long numberOfProfile = Math.round((Math.random() * 1000));
		for (int i = 0; i < numberOfProfile; i++) {
			profiles.add(getRandomProfile());
		}
		return profiles;
	}


	private Profile getRandomProfile() {
		Profile p = new Profile();
		Random rand = new Random();
		
		Ingrediant ing = new Ingrediant();
		ing.setIngrediantid(((long) rand.nextInt(100)));
		ing.setQuantite(rand.nextInt(100));
		
		
		Ingrediant ing2 = new Ingrediant();
		ing2.setIngrediantid(((long) rand.nextInt(100)));
		ing2.setQuantite(rand.nextInt(100));
		
		Ingrediant ing3 = new Ingrediant();
		ing3.setIngrediantid(((long) rand.nextInt(100)));
		ing3.setQuantite(rand.nextInt(100));
		
		Set<Ingrediant> Fridge = new HashSet<Ingrediant>();
		Fridge.add(ing);
		Fridge.add(ing2);
		Fridge.add(ing3);
		
		RecetteFav re = new RecetteFav();
		re.setRecetteid((long) 4);
	
		RecetteFav re2 = new RecetteFav();
		re2.setRecetteid((long) 12);
		
		RecetteFav re3 = new RecetteFav();
		re3.setRecetteid((long) 5);

		Set<RecetteFav> Favoris = new HashSet<RecetteFav>();
		Favoris.add(re);
		Favoris.add(re2);
		Favoris.add(re3);
		
        
		p.setPseudo(UUID.randomUUID().toString());
		p.setEmail(UUID.randomUUID().toString());
		p.setFirst_name(UUID.randomUUID().toString());
		p.setLast_name(UUID.randomUUID().toString());
		p.setScore(rand.nextInt(100));
		p.setFridge_contents(Fridge);
		p.setFavourite_recipes(Favoris);
		return p;	
	}

	
}
