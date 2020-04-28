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
public class Ratings implements Serializable{


	private static final long serialVersionUID = 7289126895404387975L;

	@Id
	@SequenceGenerator(name = "RATINGS_SEQ", sequenceName = "RATINGS_SEQ")  
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATINGS_SEQ")			
	private Long id;
	
	private long recipeID;
	
	private int rate;
	
	@ManyToOne
	@JoinColumn(name="Recipe_id",nullable = true)
	private Recipe reciperatings;

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (!(o instanceof Ratings))
            return false;
 
        Ratings other = (Ratings) o;
 
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
