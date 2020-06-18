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

// Lombok: Getter/Setter/ToString/Hashcode
@Data
@EqualsAndHashCode
@ToString

// DataBase

@Entity
@Table(name ="Ingredients")
public class Ingredients implements Serializable{
	
	private static final long serialVersionUID = -7977735657164735941L;
	
	@Id
	@SequenceGenerator(name = "INGREDIENTS_SEQ", sequenceName = "INGREDIENTS_SEQ")  
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "INGREDIENTS_SEQ")			
	private Long id;
	
	private long ingredientId;
	
	private int quantite;
}
