package domain.model;

//import javax.inject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.ArrayList;


// Lombok: Getter/Setter/ToString/Hashcode
@Data
@EqualsAndHashCode

// DataBase
@Entity
public class Recipe {
	
	public Recipe(String name2, int profilID,String date,int difficulty2, int time2) {
			this.name = name2;
			this.date = date;
			this.authorID = profilID;
			this.difficulty = difficulty2;
			this.time = time2;	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;
	
	@NotNull
	private int authorID; // ProfileID
	
	@NotNull
	//private Date date;
	private String date;
	
	//@NotNull
	//private ArrayList<Integer> ingredients; // IngredientID
	
	//@NotNull
	//private ArrayList<String> steps;
	
	//@NotNull
	//private ArrayList<CategoryEnum> category; // TODO: Test the enum
	
	@NotNull
	private int difficulty;
	
	@NotNull
	private int time;
	
	//@NotNull
	//private ArrayList<Integer> ratings;
	
	//@NotNull
	//private ArrayList<String> comments;

}
