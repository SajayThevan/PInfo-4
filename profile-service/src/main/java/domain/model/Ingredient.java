package domain.model;


import java.io.Serializable;
//import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
//import lombok.ToString;

// Yo
@Data
//@EqualsAndHashCode
//@ToString

@Entity
@Table(name ="Ingredient")  // Ingredient
public class Ingredient implements Serializable  {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4618373781528392556L;


	@Id
	@SequenceGenerator(name = "INGREDIENT_SEQ", sequenceName = "INGREDIENT_SEQ") 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INGREDIENT_SEQ")
	private Long id;
	
	private Long ingredientId;   //IngredientId
	
	
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="Profile_id",nullable = true)
	private Profile ingredientProfile;
	
	   @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	 
	        if (!(o instanceof Ingredient))
	            return false;
	 
	        Ingredient other = (Ingredient) o;
	 
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
