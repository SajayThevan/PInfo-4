package domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

//import javax.inject;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;


// Lombok: Getter/Setter/ToString/Hashcode
@Data
@EqualsAndHashCode

// DataBase

@Entity
public class Recipe{
	

	@Id
	@Column (name = "id")
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
	@ElementCollection
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Recipe_id", nullable = true)
	private List<Long> ingredients; // IngredientID
	
	@NotNull
	@ElementCollection
	List<String> steps;
	
	@NotNull
	@ElementCollection
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Recipe_id", nullable = true)
	private List<CategoryEnum> category; // 
	
	@NotNull
	private int difficulty;
	
	@NotNull
	private int time;
	
	@NotNull
	@ElementCollection
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Recipe_id", nullable = true)
	private List<Integer> ratings;
	
	@NotNull
	@ElementCollection
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Recipe_id", nullable = true)
	private List<String> comments;
	
	public void addComent(String comment) {
		this.comments.add(comment);
	}
	
	public void updateRating(int rate) {
		this.ratings.add(rate);
	}

}
