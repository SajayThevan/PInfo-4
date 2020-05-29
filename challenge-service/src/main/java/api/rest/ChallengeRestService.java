package api.rest;



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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import domain.model.Challenge;
import domain.model.ChallengeDTO;
import domain.model.Ingredient;
import domain.model.Recipe;
import domain.service.ChallengeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;


@ApplicationScoped
@Path("/challenges")
@Api(value = "challenges", authorizations = {
	      @Authorization(value="sampleoauth", scopes = {})
	    })
public class ChallengeRestService {

	@Inject
	private ChallengeService challengeService;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the challenges")

	public List<Challenge> getAll() {
		return challengeService.getAll();
	}

	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the count of challenge")
    public Long count() {
		return challengeService.count();
	}
	
	
	@GET
	@Path("/ingredients/")
	@Produces(MediaType.APPLICATION_JSON) // ?id=5&?id=6&id=5&?id=6&
	@ApiOperation(value = "Get Challenges which you need the ingredients passed in paramaters")
	public ArrayList<ChallengeDTO> getRecipesWithIngIds(@QueryParam("id") ArrayList<Long> IngredientIdList){
		return challengeService.getChallengesFromIngredientsIds(IngredientIdList);
	}
	

	// Challenge
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a specifc challenge")
	public Challenge get(@PathParam("id") Long challengeId) {
		return challengeService.get(challengeId);
	}
	 //authenticate
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value= "Remove a Challenge")
	public void removeChallengeById(@PathParam("id") long id) {
		challengeService.removeChallenge(id);
	}
	 //authenticate
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update a given challenge")
	public void update(Challenge challenge) {
		challengeService.update(challenge);

	}
	 //authenticate
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a new challenge")
	public void create(Challenge challenge) {
		challengeService.create(challenge);
	}
	
	// Ingredients
	@GET
	@Path("{id}/ingredients")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the set of ingredientsId of a challenge")
	public Set<Ingredient> getIngredients(@PathParam("id") Long challengeId) {
		return challengeService.get(challengeId).getIngredients();
	}

	// Challenge Attributes
	@GET
	@Path("{id}/name")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a specifc challenge name")
	public String getName(@PathParam("id") Long challengeId) {
		return challengeService.get(challengeId).getName();
	}

	@GET
	@Path("{id}/author")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a specifc Author Id")
	public String getAuthor(@PathParam("id") Long challengeId) {
		return challengeService.get(challengeId).getAuthorID();
	}

	// Solutions
	@GET
	@Path("{id}/solutions")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the set of recideId of a challenge")
	public Set<Recipe> getSolutions(@PathParam("id") Long challengeId) {
		return challengeService.get(challengeId).getSolutions();
	}
	
	 //authenticate check if we are a user
	@POST
	@Path("{id}/solutions")
	@ApiOperation(value ="Update recipeId list")
	public void addSolution(@PathParam("id") long challengeId, @QueryParam("solution") long recipeId) {
		challengeService.addSolution(challengeId, recipeId);
	}


}
