package domain.service;

import java.util.List;

import domain.model.Ingredient;
import domain.model.IngredientDTO;

public interface IngredientService {
	
	List<Ingredient> getAll();
	List<IngredientDTO> getAllNames();
	List<IngredientDTO> getSelectedIngredients(List<Long> ingredientID);
	Ingredient get(Long ingredientId);
	void create(Ingredient ingredient);
	double computeCalories(List<Long> ingredientID);
	double computeFat(List<Long> ingredientID);
	double computeCholesterol(List<Long> ingredientID);
	double computeProteins(List<Long> ingredientID);
	double computeSalt(List<Long> ingredientID);
	Long count();
}
