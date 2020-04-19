package domain.service;



import java.util.ArrayList;
import java.util.List;

import org.javatuples.Triplet;

import domain.model.Recipe;

public interface RecipeService {
	public void addRecipe(Recipe r);
	public void addRating(int id,int rate);
	public ArrayList<Triplet> getRecipesForProfil(int id);
	public List getRecipiesIdForProfiles(int id);
	public void addComment(String comment, int id);
	public void removeRecipe(int id);
	public ArrayList getRecipe(int id);
}