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


// DataBase

@Entity
public class Steps implements Serializable{

	private static final long serialVersionUID = 6515741718230212220L;

	
	@Id
	@SequenceGenerator(name = "STEPS_SEQ", sequenceName = "STEPS_SEQ")  
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STEPS_SEQ")			
	private Long id;
	
	private long recipeID;
	
	private String steps;
	
	@ManyToOne
	@JoinColumn(name="Recipe_id",nullable = true)
	private Recipe recipesteps;

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (!(o instanceof Steps))
            return false;
 
        Steps other = (Steps) o;
 
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
