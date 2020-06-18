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
@Table(name ="Ingredients") 
public class Ingredient implements Serializable  {
	
	private static final long serialVersionUID = -4618373781528392556L;

	@Id
	@SequenceGenerator(name = "INGREDIENTS_SEQ", sequenceName = "INGREDIENTS_SEQ") 
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "INGREDIENTS_SEQ")
	private Long id;
	
	private Long ingredientsId;  
	


}