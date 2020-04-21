package api.msg;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.model.Profile;
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Profile getRandomProfile() {
		Profile p = new Profile();
		Random rand = new Random();
		
		List<AbstractMap.SimpleEntry<Integer, Integer>> Fridge = new ArrayList<AbstractMap.SimpleEntry<Integer, Integer> >(); 
		Fridge.add(new AbstractMap.SimpleEntry(rand.nextInt(100), rand.nextInt(100))); 
        Fridge.add(new AbstractMap.SimpleEntry(rand.nextInt(100), rand.nextInt(100))); 
        Fridge.add(new AbstractMap.SimpleEntry(rand.nextInt(100), rand.nextInt(100)));
        
        List<Integer> Favorites = new ArrayList<Integer>();
        Favorites.add(rand.nextInt(100));
        Favorites.add(rand.nextInt(100));
        Favorites.add(rand.nextInt(100));
        
		p.setPseudo(UUID.randomUUID().toString());
		p.setEmail(UUID.randomUUID().toString());
		p.setFirst_name(UUID.randomUUID().toString());
		p.setLast_name(UUID.randomUUID().toString());
		p.setScore(rand.nextInt(100));
		p.setFridge_contents(Fridge);
		p.setFavourite_recipes(Favorites);
		return p;	
	}

	
}
