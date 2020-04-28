package domain.model;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;


// Lombok: Getter/Setter/ToString/Hashcode
@Data
@EqualsAndHashCode


// DataBase

@Entity
public class Recipe{
	

	@Id
	@SequenceGenerator(name = "Recipe_SEQ", sequenceName = "Recipe_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;
	
	@NotNull
	private Long authorID; // ProfileID
	
	@NotNull
	//private Date date;
	private String date;
	
	@NotNull
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER,mappedBy = "recipeing")
	private Set<Ingredients> ingredients;
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER,mappedBy = "recipesteps")
	private Set<Steps> steps;
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER,mappedBy = "recipecat")
	private Set<Category> category;
	
	@NotNull
	private int difficulty;
	
	@NotNull
	private int time;
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER,mappedBy = "reciperatings")
	private Set<Ratings> ratings;
	
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER,mappedBy = "recipeComments")
	private Set<Comments> comments;
	
	
	public void addComent(String comment) {
		Comments c = new Comments();
		c.setComment(comment);
		this.comments.add(c);
	}
	
	public void updateRating(Ratings r) {
		this.ratings.add(r);
	}

}
