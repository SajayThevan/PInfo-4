package domain.model;

import javax.inject;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import javafx.util.Pair;


// Lombok: Getter/Setter/ToString/Hashcode
@Data
@EqualsAndHashCode(callSuper=true)

// DataBase
@Entity
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	// Profile info
	@NotNull
	private String pseudo;
	
	@NotNull
	private String email;
	
	@NotNull
	private String first_name;
	
	@NotNull
	private String last_name;
	
	@NotNull
	private int score;
	
	
	// Lists
	@NotNull
	private ArrayList<Pair<int, int>> fridge_contents; // <(IngredientID, Quantity)>
		
	@NotNull
	private ArrayList<int> favourite_recipes; // <RecipeID>
	
}
