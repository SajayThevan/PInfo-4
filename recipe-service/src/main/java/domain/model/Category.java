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
public class Category implements Serializable{

	private static final long serialVersionUID = -1311239857103845925L;


	@Id
	@SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "CATEGORY_SEQ")  
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "CATEGORY_SEQ")			
	private Long id;
	
	
	private CategoryEnum category;
	
//	@ManyToOne
//	@JoinColumn(name="Recipe_id",nullable = true)
//	private Recipe recipecat;
//
//	@Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
// 
//        if (!(o instanceof Category))
//            return false;
// 
//        Category other = (Category) o;
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
//
//    @Override
//    public String toString() {
//        return this.getClass().getSimpleName() + "-" + getId();
//    }
}
