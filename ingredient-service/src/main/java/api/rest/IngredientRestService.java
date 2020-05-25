package api.rest;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import api.msg.IngredientProducer;
import domain.model.Ingredient;
import domain.model.IngredientDTO;
import domain.service.IngredientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;


@ApplicationScoped
@Path("/ingredients")
@Api(value = "ingredients", authorizations = {
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
	@Path("/selected")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the ingredients",
    notes = "Ingredients are specialized and thus might contain more fields than the one of the base class.")
	public List<IngredientDTO> getSelectedlIngredients(@QueryParam("id") List<Long> IngredientIdList) {
		return ingredientService.getSelectedIngredients(IngredientIdList);
	}

	@GET
	@Path("/names")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the ingredients as pair name-id",
    notes = "Ingredients are specialized and thus might contain more fields than the one of the base class.")
	public  List<IngredientDTO> getAllNames() {
		return ingredientService.getAllNames();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a specifc ingredient",
    notes = "Ingredients are specialized and thus might contain more fields than the one of the base class.")
	public Ingredient get(@PathParam("id") Long IngredientId) {
		return ingredientService.get(IngredientId);
	}

	@GET
	@Path("/calories")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the total of calories of a list of ingredients")
	public double computeCalories(@QueryParam("id") List<Long> IngredientIdList) {
		return ingredientService.computeCalories(IngredientIdList);
	}
	
	@GET
	@Path("/fat")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the total of calories of a list of ingredients")
	public double computeFat(@QueryParam("id") List<Long> IngredientIdList) {
		return ingredientService.computeFat(IngredientIdList);
	}
	
	@GET
	@Path("/cholesterol")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the total of calories of a list of ingredients")
	public double computeCholesterol(@QueryParam("id") List<Long> IngredientIdList) {
		return ingredientService.computeCholesterol(IngredientIdList);
	}
	
	@GET
	@Path("/proteins")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the total of calories of a list of ingredients")
	public double computeProteins(@QueryParam("id") List<Long> IngredientIdList) {
		return ingredientService.computeProteins(IngredientIdList);
	}
	
	@GET
	@Path("/salt")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the total of calories of a list of ingredients")
	public double computeSalt(@QueryParam("id") List<Long> IngredientIdList) {
		return ingredientService.computeSalt(IngredientIdList);
	}

	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the count of ingredients")
		public Long count() {
		return ingredientService.count();
	}

// TODO: DELETE EVERYTHING BELOW------------------------------------------------
	/*
	@GET
	@Path("/computeCalories/{id1}/{id2}")
	//@Path("/computeCalories")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the total of calories of a list of ingredients")
	//public int computeCalories() {
	//public int computeCalories(@PathParam("id") Long IngredientId) {
	public int computeCalories(@PathParam("id1") Long IngredientIDList1, @PathParam("id2") Long IngredientIDList2) {
		System.out.println("------------------ici test calories");
		List<Long> IngredientIDList = new ArrayList<>();
		IngredientIDList.add(IngredientIDList1);
		IngredientIDList.add(IngredientIDList2);

		//IngredientIDList2.add(Long.valueOf(2));
		//IngredientIDList2.add(Long.valueOf(3));

		//IngredientIDList2.add(IngredientId);

		System.out.println("------------------ici test calories " + IngredientIDList + "----------------");
		System.out.println("------------------ici test calories " + ingredientService.computeCalories(IngredientIDList) + "----------------");
		return ingredientService.computeCalories(IngredientIDList);
	}
	*/

	// UNUSED
	@GET
	@Path("/possibleIngredients")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get the possible ingredients compared to a string")
    public List<Object> getPossibleIngredients(@QueryParam("ing") String ingredientWanted) {
		return ingredientService.getPossibleIngredients(ingredientWanted);
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
