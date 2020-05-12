package domain.model;

import javax.persistence.DiscriminatorColumn;

//import javax.inject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;


// Lombok: Getter/Setter/ToString
@Data
//@EqualsAndHashCode(callSuper=true)

// DataBase
@Entity
public class Ingredient {
	
	// TODO: Unfinished
	
	// ID
	@NotNull
	@Id
	@SequenceGenerator(name = "INGREDIENT_SEQ", sequenceName = "INGREDIENT_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 

	@NotNull
	private String name;
	
	@NotNull
	private double kcal;
	
	@NotNull
	private double fat;
	
	@NotNull
	private double cholesterol;
	
	@NotNull
	private double protein;
	
	@NotNull
	private double salt;

	
}
