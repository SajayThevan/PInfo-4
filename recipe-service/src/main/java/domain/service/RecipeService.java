package domain.service;



import java.util.ArrayList;
import java.util.List;

import org.javatuples.Triplet;

import domain.model.Recipe;

public interface RecipeService {
	public void addRecipe(Recipe r);
	public void addRating(long id,int rate);
	public ArrayList<Triplet> getRecipesForProfil(long id);
	public List getRecipiesIdForProfiles(long id);
	public void addComment(String comment, long id);
	public void removeRecipe(long id);
	public Recipe getRecipe(long id);
	public Long count();
	public ArrayList <Long> getRecipeWithIngredientID(ArrayList<Long> ing_id);
	public ArrayList<Long> getTendancies();
	//Return an ArrayList as follow:
	//<id,Name,authorId,Date,IngredientsID,Steps,Category,Difficulty,Time,Ratings,Comments>
	
}