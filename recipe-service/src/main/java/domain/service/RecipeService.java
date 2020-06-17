package domain.service;

import java.util.ArrayList;
import java.util.List;

import domain.model.Recipe;
import domain.model.RecipeDTO;

public interface RecipeService {
	public void recipeTestVolume();
	public void addRecipe(Recipe r);
	public void addRating(long id,int rate);
	public ArrayList<RecipeDTO> getRecipesForProfil(String id);
	public ArrayList<RecipeDTO> getRecipiesIdForProfiles(String id);
	public void addComment(String comment, long id);
	public void removeRecipe(long id);
	public Recipe getRecipe(long id);
	public Long count();
	public ArrayList<RecipeDTO> getRecipeWithIngredientID(List<Long> ing_id);
	public ArrayList<RecipeDTO> getTendancies();
	public ArrayList<RecipeDTO> getRecipeOfTheMonth();
	public ArrayList<RecipeDTO> getRecipesListFromIds(List<Long> idList);
	
}