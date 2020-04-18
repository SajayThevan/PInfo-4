package domain;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;    		--These imports are not used yet maybe later--

import domain.service.ProfileServiceImpl;  
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import domain.model.Profile;

//import eu.drus.jpa.unit.api.JpaUnit;  //method TestInvocation (Use of spy) not seen in runtime ->  NoClassFindError, For the moment I am testing without it.

//@ExtendWith(JpaUnit.class)   	// annotation of eu.drus...			
@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

	@Spy
	@PersistenceContext(unitName = "ProfilePUTest")
	EntityManager em;

	@InjectMocks
	private ProfileServiceImpl ProfileService;
	
	@Test
	void test( ) {
		Profile profile = getRandomProfile();
		ProfileService.create(profile);
		System.out.println("Hey I am a profile, my ID is " + profile.getId()+ ", my email is "+profile.getEmail() +", my first name is "+profile.getFirst_name()+ ", my last name is "+profile.getLast_name()+" and my score is "+ profile.getScore());
		// The ID is null for the moment due to the issue with database.
		
		System.out.println("");
		ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> Fridge = profile.getFridge_contents();
		ArrayList<Integer> Favorites = profile.getFavourite_recipes();
		
		for (int i = 0; i < Fridge.size(); i++) {
			System.out.println("Hey I am the fridge my element " +i+" contain "+Fridge.get(i));
		}
		System.out.println("");
		for (int i = 0; i < Favorites.size(); i++) {
			System.out.println("Hey I am the favorites my element " +i+" contain "+Favorites.get(i));
		}	
		
	}
		
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Profile getRandomProfile() {
		Profile p = new Profile();
		Random rand = new Random();
		
		ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> Fridge = new ArrayList<AbstractMap.SimpleEntry<Integer, Integer> >(); 
		Fridge.add(new AbstractMap.SimpleEntry(rand.nextInt(100), rand.nextInt(100))); 
        Fridge.add(new AbstractMap.SimpleEntry(rand.nextInt(100), rand.nextInt(100))); 
        Fridge.add(new AbstractMap.SimpleEntry(rand.nextInt(100), rand.nextInt(100)));
        
        ArrayList<Integer> Favorites = new ArrayList<Integer>();
        Favorites.add(rand.nextInt(100));
        Favorites.add(rand.nextInt(100));
        Favorites.add(rand.nextInt(100));
        
		// Not setting an ID because i think it would be automatic in the future.
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
