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
import lombok.EqualsAndHashCode;
import lombok.ToString;


// Lombok: Getter/Setter/ToString/Hashcode
@Data
@EqualsAndHashCode
@ToString

// DataBase

@Entity
@Table(name ="Ingredients")
public class Ingredients implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -7977735657164735941L;
	
	@Id
	@SequenceGenerator(name = "INGREDIENTS_SEQ", sequenceName = "INGREDIENTS_SEQ")  
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INGREDIENTS_SEQ")			
	private Long id;
	
	private long IngredientID;
	
	private int quantite;
	
//	@ManyToOne
//	@JoinColumn(name="Recipe_id",nullable = true)
//	private Recipe recipeing;

//	@Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
// 
//        if (!(o instanceof Ingredients))
//            return false;
// 
//        Ingredients other = (Ingredients) o;
// 
//        return id != null &&
//               id.equals(other.getId());
//    }
// 
//    @Override
//    public int hashCode() {
//        return 31;
//    }
//	
//
//    @Override
//    public String toString() {
//        return this.getClass().getSimpleName() + "-" + getId();
//    }
	

}
