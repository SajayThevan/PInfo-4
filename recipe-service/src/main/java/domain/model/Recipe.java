package domain.model;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;


// Lombok: Getter/Setter/ToString/Hashcode
@Data
@EqualsAndHashCode


// DataBase

@Entity
@Table(name ="Recipe")
public class Recipe implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5056698075957095958L;

	@Id
	@SequenceGenerator(name = "Recipe_SEQ", sequenceName = "Recipe_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="Recipe_SEQ")
	private Long id;

	@NotNull
	private String name;
	
	@NotNull
	private Long authorID; // ProfileID
	
	@NotNull
	//private Date date;
	private String date;
	
	@NotNull
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Recipe_ID", nullable = true)
	private Set<Ingredients> ingredients;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Recipe_ID", nullable = true)
	private Set<Steps> steps;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Recipe_ID", nullable = true)
	private Set<Category> category;
	
	@NotNull
	private int difficulty;
	
	@NotNull
	private int time;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Recipe_ID", nullable = true)
	private Set<Ratings> ratings;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Recipe_ID", nullable = true)
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
