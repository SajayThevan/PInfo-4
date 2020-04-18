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
import javax.ws.rs.core.MediaType;

//import api.msg.InstrumentProducer;
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
	/*
	@Inject
	private IngredientProducer ingredientProducer;
	*/
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a specifc instrument",
    notes = "Instruments are specialized and thus might contain more fields than the one of the base class.")
	public Ingredient get(@PathParam("id") Long IngredientId) {
		return ingredientService.get(IngredientId);
	}
	
	@GET
	@Path("/computeCalories")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the count of instrument")
    public int computeCalories(@PathParam("id") List<Integer> IngredientID) {
		return ingredientService.computeCalories(IngredientID);
	}
	
	@GET
	@Path("/PossibleIngredients")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get the possible ingredients")
    public List<Object> getPossibleIngredients(String possibleIngredient) {
		return ingredientService.getPossibleIngredients(possibleIngredient);
	}
	
	/*
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update a given instrument",
    notes = "Instruments are specialized and thus might contain more fields than the one of the base class.")
	public void upadte(Instrument instrument) {
		instrumentService.update(instrument);
		instrumentProducer.send(instrument);
	}
	
	@POST
	@Path("propagateAllIngredients")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Propagate all instruments to the bus to sync up downstream services",
    notes = "Ingredients are specialized and thus might contain more fields than the one of the base class.")
	public void propagateAllInstruments() {
		ingredientProducer.sendAllInstruments();
	}
	*/
}