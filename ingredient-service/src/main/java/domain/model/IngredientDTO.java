package domain.model;

import lombok.Data;

 @Data
public class IngredientDTO {
	public IngredientDTO(Long id2, String name2) {
		this.id = id2;
		this.name = name2;
	}
	private Long id;
	private String name;
}
