package domain.model;
// test
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
// Test

// Lombok: Getter/Setter/ToString
@Data

// DataBase
@Entity
@Table(name ="Profile")
public class Profile implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2425396202264911160L;

	@Id
	@SequenceGenerator(name = "PROFILE_SEQ", sequenceName = "PROFILE_SEQ")  
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_SEQ")			
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "ingprofile")
	private Set<Ingrediant> fridge_contents; // <(IngredientID, Quantity)> 
	
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "recetteprofile")
	private Set<RecetteFav> favourite_recipes; // <(IngredientID, Quantity)> 
		
	
	
}
