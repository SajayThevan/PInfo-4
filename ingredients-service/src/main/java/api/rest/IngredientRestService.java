package api.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import api.msg.IngredientProducer;
import domain.model.Ingredient;
import domain.service.IngredientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@ApplicationScoped
@Path("/ingredient")
@Api(value = "ingredient", authorizations = {
	      @Authorization(value="sampleoauth", scopes = {})
	    })
public class IngredientRestService {

	@Inject
	private IngredientService ingredientService;
	
	@Inject
	private IngredientProducer ingredientProducer;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the ingredients",
    notes = "Ingredients are specialized and thus might contain more fields than the one of the base class.")
	public List<Ingredient> getAll() {
		return ingredientService.getAll();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a specifc ingredient",
    notes = "Ingredients are specialized and thus might contain more fields than the one of the base class.")
	public Ingredient get(@PathParam("id") Long IngredientId) {
		//System.out.println("id : " + IngredientId);
		//System.out.println("corresponding ingredient : " + ingredientService.get(IngredientId));
		return ingredientService.get(IngredientId);
	}
	
	@GET
	@Path("/computeCalories")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the total of calories of a list of ingredients")
    public int computeCalories(List<Long> IngredientID) {
		return ingredientService.computeCalories(IngredientID);
	}
	
	@GET
	@Path("/PossibleIngredients/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get the possible ingredients compared to a string")
    public List<Object> getPossibleIngredients(@PathParam("name") String ingredientWanted) {
		return ingredientService.getPossibleIngredients(ingredientWanted);
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the count of ingredients")
    public Long count() {
		System.out.println("------------------ici test count" + ingredientService.count() + "----------------");
		return ingredientService.count();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a new ingredient",
    notes = "Ingredients are specialized and thus might contain more fields than the one of the base class.")
	public void create(Ingredient ingredient) {
		ingredientService.create(ingredient);
		ingredientProducer.send(ingredient);
	}
	
	@POST
	@Path("propagateAllIngredients")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Propagate all ingredients to the bus to sync up downstream services",
    notes = "Ingredients are specialized and thus might contain more fields than the one of the base class.")
	public void propagateAllIngredients() {
		ingredientProducer.sendAllIngredients();
	}

}