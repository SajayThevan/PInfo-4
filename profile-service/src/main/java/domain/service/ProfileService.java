package domain.service;

import java.util.List;
import domain.model.Profile;

public interface ProfileService {
	List<Profile> getAll();
	void update(Profile profile);
	void create(Profile profile);
	Profile get(String profileId);
	Long count();
	void addIngredient(String id, long ingredientId, int quantity);
	void addFavourite(String id, long recipeId);
	void removeProfile(String id);
	void removeIngredient(String id, long ingredientId);
	void removeFavourite(String id, long recipeId);
	boolean checkProfile(String profileId);



	
}