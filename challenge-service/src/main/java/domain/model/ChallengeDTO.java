package domain.model;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ChallengeDTO {

	public ChallengeDTO(long id, String name, String authorID, Set<Ingredient> ingredients ) {
		this.id = id;
		this.name = name;
		this.authorID = authorID;
		this.ing = new ArrayList<>();
		for (Ingredient in: ingredients) {
			this.ing.add(in.getIngredientsId());
		}
	}

	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String authorID;


	private ArrayList<Long> ing;

}
