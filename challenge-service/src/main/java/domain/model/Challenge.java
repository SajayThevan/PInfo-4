package domain.model;



import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



// Lombok: Getter/Setter/ToString/Hashcode
@Data
@EqualsAndHashCode(callSuper=true)

// DataBase
@Entity
@Table(name ="Profile")
public class Challenge implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4080220078360449057L;

	@Id
	@SequenceGenerator(name = "CHALLENGE_SEQ", sequenceName = "CHALLENGE_SEQ")  
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHALLENGE_SEQ")			
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private int authorID; // ProfileID
	
	@NotNull
	private List<Integer> ingredients; // IngredientID's
	
	@NotNull
	@Inject
	private List<Recipe> solutions;
	
}
