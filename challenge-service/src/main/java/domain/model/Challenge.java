package domain.model;




import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serializable;
import java.util.Set;





@Data
@Entity
@Table(name ="Challenge")
public class Challenge implements Serializable {

	private static final long serialVersionUID = -4080220078360449057L;

	@Id
	@SequenceGenerator(name = "CHALLENGE_SEQ", sequenceName = "CHALLENGE_SEQ")  
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHALLENGE_SEQ")			
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private Long authorID; // ProfileID
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "CHALLENGE_ID", nullable = true)
	private Set<Ingredient> ingredients; // <(IngredientID, Quantity)> 
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "CHALLENGE_ID", nullable = true)
	private Set<Recipe> solutions; // <(recipeId)> 
//	
}
