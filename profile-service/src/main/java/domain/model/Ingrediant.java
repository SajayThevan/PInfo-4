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
// Yo
@Data

@Entity
@Table(name ="Ingrediant")
public class Ingrediant implements Serializable  {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4618373781528392556L;


	@Id
	@SequenceGenerator(name = "INGREDIANT_SEQ", sequenceName = "INGREDIANT_SEQ") 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INGREDIANT_SEQ")
	private Long id;
	
	private Long Ingrediantid;
	
	
	private int quantite;
	
	@ManyToOne
	@JoinColumn(name="Profile_id",nullable = true)
	private Profile ingprofile;
	
	   @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	 
	        if (!(o instanceof Ingrediant))
	            return false;
	 
	        Ingrediant other = (Ingrediant) o;
	 
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
