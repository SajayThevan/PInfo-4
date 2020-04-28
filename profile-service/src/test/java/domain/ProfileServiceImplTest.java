package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import domain.service.ProfileServiceImpl; 
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
import domain.model.Profile;
import domain.model.RecipeFav;
import domain.model.Ingredient;
import eu.drus.jpa.unit.api.JpaUnit; 

@ExtendWith(JpaUnit.class)   			
@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

	@Spy
	@PersistenceContext(unitName = "ProfilePUTest")
	EntityManager em;

	@InjectMocks
	private ProfileServiceImpl profileService;
	
	
	@Test
	void testGetAll() {
		System.out.println("-----------------DEBUT TEST GETALL-----------------");
		List<Profile> profiles = profileService.getAll();
		int size = profiles.size();
		
		profileService.create(getRandomProfile());
		profileService.create(getRandomProfile());
		profileService.create(getRandomProfile());
		profileService.create(getRandomProfile());
		
		assertEquals(size + 4, profileService.getAll().size());
		System.out.println("-----------------TEST GETALL TERMINE-----------------");
	}

	@Test
	void testCount() {
		System.out.println("-----------------DEBUT TEST COUNT-----------------");
		List<Profile> profiles = profileService.getAll();
		int size = profiles.size();
		
		profileService.create(getRandomProfile());
		profileService.create(getRandomProfile());
		profileService.create(getRandomProfile());
		profileService.create(getRandomProfile());
		
		Long count = profileService.count();
		assertEquals(size + 4, count);
		System.out.println("-----------------TEST COUNT TERMINE-----------------");
	}
	
	@Test
	void testUpdate() {
		System.out.println("-----------------DEBUT TEST UPDATE-----------------");
		profileService.create(getRandomProfile());
		Profile profile = profileService.getAll().get(0);
		assertNotNull(profile);
		Long id = profile.getId();
		profile.setFirst_name("Deniz");
		profileService.update(profile);
		profile = profileService.get(id);
		assertEquals("Deniz", profile.getFirst_name());
		System.out.println("-----------------TEST UPDATE TERMINE-----------------");
	}
	
	@SuppressWarnings("serial")
	@Test
	void testUpdateNonExistant() {
		System.out.println("-----------------DEBUT TEST UPDATE NON EXISTANT-----------------");
		Profile i = new Profile() {
			@Override
			public Long getId() {
				return Long.MAX_VALUE;
			}
		};
		assertThrows(IllegalArgumentException.class, () -> {
			profileService.update(i);
		});
		System.out.println("-----------------TEST UPDATE NON EXISTANT TERMINE-----------------");
	}

	@Test
	void testGet() {
		System.out.println("-----------------DEBUT TEST GET-----------------");
		profileService.create(getRandomProfile());
		Profile profile = profileService.getAll().get(0);
		assertNotNull(profile);
		Long id = profile.getId();
		Profile getProfile = profileService.get(id);
		System.out.println("---------------------"+profile.getFridge_contents()+"----------------");
		assertEquals(profile.getFirst_name(), getProfile.getFirst_name());     
		System.out.println("-----------------TEST GET TERMINE-----------------");
	}

	@Test
	void testGetNonExistant() {
		System.out.println("-----------------DEBUT TEST GET NON EXISTANT-----------------");
		List<Profile> profiles = profileService.getAll();
		System.out.println("testGetNonExistant:" + profiles.size());

		assertNull(profileService.get(Long.MAX_VALUE));
		System.out.println("-----------------TEST GET NON EXISTANT TERMINE-----------------");
	}

	@Test
	void testCreate() {
		System.out.println("-----------------DEBUT TEST CREATION PROFILE-----------------");
		Profile profile = getRandomProfile();
		profileService.create(profile);
		assertNotNull(profile.getId());
		System.out.println("-----------------TEST CREATION PROFILE TERMINE-----------------");
	}


	@Test
	void testCreateDuplicate() {
		System.out.println("-----------------DEBUT TEST CREATION DUPLICAT-----------------");
		Profile profile = getRandomProfile();
		profileService.create(profile);
		assertThrows(IllegalArgumentException.class, () -> {
			profileService.create(profile);
		});
		System.out.println("-----------------TEST CREATION DUPLICAT TERMINE-----------------");
	}
	

	private Profile getRandomProfile() {
		Profile p = new Profile();
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
		p.setFirst_name(UUID.randomUUID().toString());
		p.setLast_name(UUID.randomUUID().toString());
		p.setScore(rand.nextInt(100));
		p.setFridge_contents(Fridge);
		p.setFavourite_recipes(Favoris);
		return p;	
	}
	
	
}
