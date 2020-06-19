package domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// Lombok: Getter/Setter/ToString/Hashcode
@Data
@EqualsAndHashCode
@ToString
// DataBase
@Entity
public class Ratings implements Serializable{

	private static final long serialVersionUID = 7289126895404387975L;

	@Id
	@SequenceGenerator(name = "RATINGS_SEQ", sequenceName = "RATINGS_SEQ")  
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "RATINGS_SEQ")			
	private Long id;
	
	private int rate;
}
