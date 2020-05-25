package domain.model;
import java.util.ArrayList;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class RecipeDTO {
	
	
	
	public RecipeDTO(Long id2, String name2, Set<Ingredients> ingredients,String authorId2,Set<Ratings> rat) {
		this.id = id2;

		this.ing = new ArrayList<Long>();
		for (Ingredients in: ingredients) {
			this.ing.add(in.getIngredientID());
		}
		this.name = name2;
		this.authorId = authorId2;
		for (Ratings i: rat) {
			this.mean += i.getRate();
		}
		this.mean = this.mean / rat.size();
	}
	private long id;
	private ArrayList<Long> ing;
	private String name;
	private String authorId;
	private float mean;
	
}
