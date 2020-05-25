package domain.service;

import java.util.List;
import domain.model.Profile;

public interface ProfileService {
	List<Profile> getAll();
	void update(Profile profile);
	void create(Profile profile);
	Profile get(Long profileId);
	Long count();
	void removeProfile(long id);
	void addIngredient(long challengeId, long ingredientId, int quantity);
	void addFavourite(long id, long recipeId);
	void removeFavourite(long id, long recipeId);
	void removeIngredient(long profileId, long ingredientId);
	boolean checkProfile(Long profileId);



	
}