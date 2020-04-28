package domain.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import domain.model.Ingredient;

public interface IngredientService {
	
	List<Ingredient> getAll();
	Ingredient get(Long ingredientId);
	void create(Ingredient ingredient);
	//int computeCalories(Array<int IngredientID>);  
	// use instead : https://www.w3schools.com/java/java_arrays.asp
	int computeCalories(List<Long> IngredientID);
	// OR :
	//int computeCalories(int[] IngredientID);
	// ------
	//Array<(int ingredientId, String name)> getPossibleIngredients(String enter)
	List<Object> getPossibleIngredients(String possibleIngredient);
	// OR
	// Object[] getPossibleIngredients(String enter);
	// https://docs.oracle.com/javase/specs/jls/se7/html/jls-10.html
	//https://stackoverflow.com/questions/16363547/how-to-declare-an-array-of-different-data-types
	Long count();
}
