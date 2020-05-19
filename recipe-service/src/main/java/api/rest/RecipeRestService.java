package api.rest;

import java.util.ArrayList;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javatuples.Triplet;


import domain.model.Recipe;

import domain.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@ApplicationScoped
@Path("/recipes")
@Api(value = "recipes", authorizations = {
	      @Authorization(value="sampleoauth", scopes = {})
	    })
public class RecipeRestService {

	@Inject
	private RecipeService rs;


	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a new Recipe",  notes = "Create a new Recip.")
	public void createRecipe(Recipe r) {
		rs.addRecipe(r);
	}

	@PUT
	@Path("/rate/{recipeId}/{rate}")
	@ApiOperation(value ="Update recipe ratings")
	public void addRates(@PathParam("recipeId") long id, @PathParam("rate")int rate) {
		rs.addRating(id,rate);
	}

	@SuppressWarnings("rawtypes")
	@GET
	@Path("/recipesProfil/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value ="Get Recipes for profil ID")
	public ArrayList<Triplet> getRecipesForProfilRest(@PathParam("id") long id) {

		return rs.getRecipesForProfil(id);
	}

	@PUT
	@Path("/addComments/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Add a comment")
	public void addCommentRest(@PathParam("id") long id, String comment) {

		rs.addComment(comment,id);
	}


	@DELETE
	@Path("/rm/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value= "Remove a Recipe")
	public void removeRecipeRest(@PathParam("id") long id) {
		rs.removeRecipe(id);
	}

	@GET
	@Path("/getRecipe/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a full Recipe")
	public Recipe getRecipeRest(@PathParam("id") long id) {
		Recipe a = rs.getRecipe(id);
		return a;
	}

	@GET
	@Path("/recipesWithIngIds")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get recipes wich you need the ingredients passed in paramaters")
	public ArrayList<Long> getRecipesWithIngIds(ArrayList<Long> ing_id){
		return rs.getRecipeWithIngredientID(ing_id);
	}

	@GET
	@Path("/tendancies")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "return the 20 best recipes")
	public ArrayList<Long> getTendanciess(){
		return rs.getTendancies();
	}

	@GET
	@Path("/recipeOfTheMonth")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "return the 20 best recipes")
	public ArrayList<Long> getRecipeOfTheMonth(){
		return rs.getRecipeOfTheMonth();
	}

	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the count of instrument")
    public Long count() {
		return rs.count();
	}




}
