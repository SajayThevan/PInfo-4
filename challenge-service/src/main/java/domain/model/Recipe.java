package domain.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude= {"id"})
@ToString
@Entity
@Table(name ="Recipe") 
public class Recipe implements Serializable  {

	private static final long serialVersionUID = 4370273419623542742L;

	@Id
	@SequenceGenerator(name = "RECIPE_SEQ", sequenceName = "RECIPE_SEQ") 
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "RECIPE_SEQ")
	private Long id;
	
	private Long recipeId; 
}