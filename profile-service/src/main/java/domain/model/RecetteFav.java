package domain.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data

@Entity
@Table(name ="RecetteFav")
public class RecetteFav implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4370273419623542742L;

	@Id
	@SequenceGenerator(name = "RECETTEFAV_SEQ", sequenceName = "RECETTEFAV_SEQ") 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECETTEFAV_SEQ")
	private Long id;
	
	private Long Recetteid;
	
	@ManyToOne
	@JoinColumn(name="Profile_id",nullable = true)
	private Profile recetteprofile;
	
	   @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	 
	        if (!(o instanceof RecetteFav))
	            return false;
	 
	        RecetteFav other = (RecetteFav) o;
	 
	        return id != null &&
	               id.equals(other.getId());
	    }
	 
	    @Override
	    public int hashCode() {
	        return 31;
	    }
	    
	    @Override
	    public String toString() {
	        return this.getClass().getSimpleName() + "-" + getId();
	    }
	

}
