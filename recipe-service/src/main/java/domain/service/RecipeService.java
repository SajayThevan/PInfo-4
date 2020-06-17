package domain.service;

import java.util.List;
import domain.model.Recipe;
import domain.model.RecipeDTO;

public interface RecipeService {
	public void recipeTestVolume();
	public void addRecipe(Recipe r);
	public void addRating(long id,int rate);
	public List<RecipeDTO> getRecipesForProfil(String id);
	public List<RecipeDTO> getRecipiesIdForProfiles(String id);
	public void addComment(String comment, long id);
	public void removeRecipe(long id);
	public Recipe getRecipe(long id);
	public Long count();
	public List<RecipeDTO> getRecipeWithIngredientID(List<Long> ingId);
	public List<RecipeDTO> getTendancies();
	public List<RecipeDTO> getRecipeOfTheMonth();
	public List<RecipeDTO> getRecipesListFromIds(List<Long> idList);
	
}