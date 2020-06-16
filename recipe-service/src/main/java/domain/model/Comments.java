package domain.model;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Comments implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 8612291428356986734L;

	@Id
	@SequenceGenerator(name = "COMMENTS_SEQ", sequenceName = "COMMENTS_SEQ")  
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "COMMENTS_SEQ")			
	private Long id;
	
	
	private String comment;

}
