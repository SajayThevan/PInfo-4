package domain.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;



@Data
@Entity
@Table(name ="Profile")
public class Profile implements Serializable {
	
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
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private int score;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "PROFILE_ID", nullable = true)
	private Set<Ingredient> fridgeContents; // <(IngredientID, Quantity)> 
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "PROFILE_ID", nullable = true)
	private Set<RecipeFav> favouriteRecipes; // <(recipeId)> 
		
}
