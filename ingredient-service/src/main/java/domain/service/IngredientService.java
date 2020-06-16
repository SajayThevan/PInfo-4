package domain.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import domain.model.Ingredient;
import domain.model.IngredientDTO;

public interface IngredientService {
	
	List<Ingredient> getAll();
	List<IngredientDTO> getAllNames();
	List<IngredientDTO> getSelectedIngredients(List<Long> IngredientID);
	Ingredient get(Long ingredientId);
	void create(Ingredient ingredient);
	double computeCalories(List<Long> IngredientID);
	double computeFat(List<Long> IngredientID);
	double computeCholesterol(List<Long> IngredientID);
	double computeProteins(List<Long> IngredientID);
	double computeSalt(List<Long> IngredientID);
	Long count();
}
