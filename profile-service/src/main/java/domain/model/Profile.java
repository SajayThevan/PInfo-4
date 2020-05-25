package domain.model;


import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;



@Data
@Entity
@Table(name ="Profile")
public class Profile  {
	
	@Id
	@Column(name = "id")		
	private String id;
	
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
