package domain.model;

//import javax.inject; In comment because not in the "Equivalent" code of Steve -> Instrument.java
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

//import io.swagger.annotations.ApiModel; // Need a Maven Build or Install to generate swagger files

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
//import javafx.util.Pair; // Need sudo apt-get install openjfx --> import used for the definition of fridge content


// Lombok: Getter/Setter/ToString/Hashcode
@Data
//@EqualsAndHashCode(callSuper=true) //In comment because not in the "Equivalent" code of Steve -> Instrument.java

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
//	@NotNull
//	private ArrayList<Pair<int, int>> fridge_contents; // <(IngredientID, Quantity)> // In comment because of error
//		
//	@NotNull
//	private ArrayList<int> favourite_recipes; // <RecipeID> // In comment because of error
	
}
