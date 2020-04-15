package domain.model;

import javax.inject;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;


// Lombok: Getter/Setter/ToString/Hashcode
@Data
@EqualsAndHashCode(callSuper=true)

// DataBase
@Entity
public class Ingredient {
	
	// TODO: Unfinished
	
	// ID
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long int id; // Maybe too long?

	@NotNull
	private String name;
	
	@NotNull
	private Int calories;
	
	// TODO: More attributes???
}
