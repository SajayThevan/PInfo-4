package domain.service;


import java.lang.reflect.Array;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.constraints.NotNull;

import domain.model.CategoryEnum;
import domain.model.Recipe;
import lombok.Data;

@ApplicationScoped
public class RecipeServiceImpl implements RecipeService {
	
	@PersistenceContext(unitName = "RecipePU")
	private EntityManager em;
	
	
	public RecipeServiceImpl() {
	}

	public RecipeServiceImpl(EntityManager em) {
		this();
		this.em = em;
	}
	
	
	//TODO: Implement that shit
	
	public void addRecipe(Recipe r) {
		if (r.getId() != null) {
			throw new IllegalArgumentException("Instrument already exists : " + r.getId());
		}
		em.persist(r);
	}

	
}
