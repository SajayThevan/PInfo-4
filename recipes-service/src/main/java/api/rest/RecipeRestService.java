package api.rest;

import java.util.ArrayList;
import java.util.List;

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

import api.msg.RecipeProducer;
import domain.model.Recipe;

import domain.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@ApplicationScoped
@Path("/Recipe")
@Api(value = "Recipe", authorizations = {
	      @Authorization(value="sampleoauth", scopes = {})
	    })
public class RecipeRestService {

	@Inject
	private RecipeService rs;
	@Inject
	private RecipeProducer rp;
	
	
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
	
	@GET
	@Path("/recipesProfil/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value ="Get Recipes for profil ID")
	public ArrayList<Triplet> getRecipesForProfilRest(@PathParam("id") long id) {
		return rs.getRecipesForProfil(id);
	}

	@PUT
	@Path("addComments/{comment}/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add a comment")
	public void addCommentRest(@PathParam("comment") String co, @PathParam("id") long id) {
		rs.addComment(co,id);
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a full Recipe")
	public ArrayList getRecipeRest(@PathParam("id") long id) {
		return rs.getRecipe(id);
	}
	
	
	
	/*
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the instruments",
    notes = "Instruments are specialized and thus might contain more fields than the one of the base class.")
	public List<Instrument> getAll() {
		return instrumentService.getAll();
	}

	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the count of instrument")
    public Long count() {
		return instrumentService.count();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a specifc instrument",
    notes = "Instruments are specialized and thus might contain more fields than the one of the base class.")
	public Instrument get(@PathParam("id") Long instrumentId) {
		return instrumentService.get(instrumentId);
	}
	

	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update a given instrument",
    notes = "Instruments are specialized and thus might contain more fields than the one of the base class.")
	public void upadte(Instrument instrument) {
		instrumentService.update(instrument);
		instrumentProducer.send(instrument);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a new instrument",
    notes = "Instruments are specialized and thus might contain more fields than the one of the base class.")
	public void create(Instrument instrument) {
		instrumentService.create(instrument);
		instrumentProducer.send(instrument);
	}

	
	@POST
	@Path("propagateAllInstruments")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Propagate all instruments to the bus to sync up downstream services",
    notes = "Instruments are specialized and thus might contain more fields than the one of the base class.")
	public void propagateAllInstruments() {
		instrumentProducer.sendAllInstruments();
	}
	
	*/
}