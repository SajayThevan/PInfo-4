package domain.model;

//import java.math.BigDecimal;
//import java.util.Date;
//import javax.persistence.Column;
//import javax.persistence.DiscriminatorColumn;
//import javax.persistence.InheritanceType;
//import java.util.ArrayList;					
//import io.swagger.annotations.ApiModel; -- These are not used for the moment --  If error fix the project to add the libraries.

import java.util.AbstractMap;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import lombok.Data;

// Lombok: Getter/Setter/ToString
@Data

// DataBase
@Entity
public class Profile {
	
	@Id
	@SequenceGenerator(name = "Profile_SEQ", sequenceName = "Profile_SEQ")  //Defines a primary key generator that may be referenced by name when a generator element is specified for the GeneratedValue annotation.
	@GeneratedValue(strategy = GenerationType.IDENTITY)						//The scope of the generator name is global to the persistence unit => Maybe a link with the database?
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
	

	@NotNull
	@ElementCollection
	private List<AbstractMap.SimpleEntry<Integer, Integer>> fridge_contents; // <(IngredientID, Quantity)> 
		
	@NotNull
	@ElementCollection
	private List<Integer> favourite_recipes; // <RecipeID> //
	
}
