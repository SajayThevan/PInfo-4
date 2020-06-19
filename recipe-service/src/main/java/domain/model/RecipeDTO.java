package domain.model;

import java.util.ArrayList;
import java.util.Set;

import lombok.Data;

@Data
public class RecipeDTO {
	
	public RecipeDTO(Long id2, String name2, Set<Ingredients> ingredients,String authorId2,Set<Ratings> rat, String path) {
		this.id = id2;

		this.ing = new ArrayList<>();
		for (Ingredients in: ingredients) {
			this.ing.add(in.getIngredientId());
		}
		this.name = name2;
		this.authorId = authorId2;
		for (Ratings i: rat) {
			this.mean += i.getRate();
		}
		this.mean = this.mean / rat.size();
		this.path = path;
	}
	private long id;
	private ArrayList<Long> ing;
	private String name;
	private String authorId;
	private float mean;
	private String path;
	
}
