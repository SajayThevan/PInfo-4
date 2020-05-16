package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import domain.service.ProfileServiceImpl; 
import java.util.HashSet;
import java.util.Iterator;
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
		List<Profile> profiles = profileService.getAll();
		int size = profiles.size();
		
		profileService.create(getRandomProfile());
		profileService.create(getRandomProfile());
		profileService.create(getRandomProfile());
		profileService.create(getRandomProfile());
	
		assertEquals(size + 4, profileService.getAll().size());
	}

	@Test
	void testCount() {
		List<Profile> profiles = profileService.getAll();
		int size = profiles.size();
		
		profileService.create(getRandomProfile());
		profileService.create(getRandomProfile());
		profileService.create(getRandomProfile());
		profileService.create(getRandomProfile());
		
		Long count = profileService.count();
		assertEquals(size + 4, count);
	}
	
	@Test
	void testUpdate() {
		profileService.create(getRandomProfile());
		Profile profile = profileService.getAll().get(0);
		assertNotNull(profile);
		Long id = profile.getId();
		profile.setFirstName("Deniz");
		profileService.update(profile);
		profile = profileService.get(id);
		assertEquals("Deniz", profile.getFirstName());
	}
	
	@SuppressWarnings("serial")
	@Test
	void testUpdateNonExistant() {
		Profile i = new Profile() {
			@Override
			public Long getId() {
				return Long.MAX_VALUE;
			}
		};
		assertThrows(IllegalArgumentException.class, () -> {
			profileService.update(i);
		});
	}

	@Test
	void testGet() {
		profileService.create(getRandomProfile());
		Profile profile = profileService.getAll().get(0);
		assertNotNull(profile);
		Long id = profile.getId();
		Profile getProfile = profileService.get(id);
		assertEquals(profile.getFirstName(), getProfile.getFirstName());     
	}

	@Test
	void testGetNonExistant() {
		assertNull(profileService.get(Long.MAX_VALUE));
	}

	@Test
	void testCreate() {
		Profile profile = getRandomProfile();
		profileService.create(profile);
		assertNotNull(profile.getId());
	}


	@Test
	void testCreateDuplicate() {
		Profile profile = getRandomProfile();
		profileService.create(profile);
		assertThrows(IllegalArgumentException.class, () -> {
			profileService.create(profile);
		});
	}
	
	@Test
	void testAddIngredientById() {
		Ingredient ing = new Ingredient();
		ing.setIngredientId((long) 10);
		ing.setQuantity(20);
		Profile profile = getRandomProfile();
		Set<Ingredient> oldListIng = profile.getFridgeContents();
		Long idIng = ing.getIngredientId();
		int quantity = ing.getQuantity();
		profileService.create(profile);
		Long idProfile = profile.getId();
		oldListIng.add(ing);
		profileService.addIngredient(idProfile, idIng, quantity);
		Profile profileDB = profileService.get(idProfile);
		Set<Ingredient> newListIng = profileDB.getFridgeContents();
		assertEquals(newListIng,oldListIng);		
	}
	
	@Test
	void testAddFavouriteById() {
		RecipeFav fav = new RecipeFav();
		fav.setRecipeId((long) 20);
		Profile profile = getRandomProfile();
		Set<RecipeFav> oldListFav = profile.getFavouriteRecipes();
		Long idFav = fav.getRecipeId();
		profileService.create(profile);
		Long idProfile = profile.getId();
		oldListFav.add(fav);
		profileService.addFavourite(idProfile, idFav);
		Profile profileDB = profileService.get(idProfile);
		Set<RecipeFav> newListFav = profileDB.getFavouriteRecipes();
		assertEquals(newListFav,oldListFav);		
	}
	
	@Test
	void testRemoveProfile() {
		Profile profile = getRandomProfile();
		profileService.create(profile);
		Long profileId = profile.getId();
		profileService.removeProfile(profileId);
		assertThrows(IllegalArgumentException.class, () -> {
			profileService.removeProfile(profileId);
		});
	}
	
	@Test
	void testRemoveIngredientById() {  
		Profile profile = getRandomProfile();
		Iterator<Ingredient> iterator = profile.getFridgeContents().iterator();
		iterator.next().getIngredientId();
		Long secondId = iterator.next().getIngredientId();
		profileService.create(profile);
		Long idProfile = profile.getId();
		Profile profileDB = profileService.get(idProfile);
		Set<Ingredient> newListIng = profile.getFridgeContents();
		Long idIng = newListIng.iterator().next().getIngredientId();
		profileService.removeIngredient(idProfile, idIng);
		Long newFirstId = profileDB.getFridgeContents().iterator().next().getIngredientId();
		assertEquals(newFirstId,secondId);		
	}
	
	@Test
	void testRemoveFavouriteById() {  
		Profile profile = getRandomProfile();
		Iterator<RecipeFav> iterator = profile.getFavouriteRecipes().iterator();
		iterator.next().getRecipeId();
		Long secondId = iterator.next().getRecipeId();
		profileService.create(profile);
		Long idProfile = profile.getId();
		Profile profileDB = profileService.get(idProfile);
		Set<RecipeFav> newListFav = profile.getFavouriteRecipes();
		Long idFav = newListFav.iterator().next().getRecipeId();
		profileService.removeFavourite(idProfile, idFav);
		Long newFirstId = profileDB.getFavouriteRecipes().iterator().next().getRecipeId();
		assertEquals(newFirstId,secondId);		
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
		p.setFirstName(UUID.randomUUID().toString());
		p.setLastName(UUID.randomUUID().toString());
		p.setScore(rand.nextInt(100));
		p.setFridgeContents(Fridge);
		p.setFavouriteRecipes(Favoris);
		return p;	
	}
	
}
