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
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.Set;

import api.msg.ProfileProducer;
import domain.model.Ingredient;
import domain.model.Profile;
import domain.model.RecipeFav;
import domain.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;


@ApplicationScoped
@Path("/profiles")
@Api(value = "profiles", authorizations = {
	      @Authorization(value="sampleoauth", scopes = {})
	    })
public class ProfileRestService {

	@Inject
	private ProfileService profileService;
	@Inject
	private ProfileProducer profileProducer;

	@GET   //Remove for production
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the profiles")
	public List<Profile> getAll() {
		return profileService.getAll();
	}

	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the count of profile")
	public Long count() {
		return profileService.count();
	}
	//authenticate
	// Profile
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a specific profile")
	public Profile get(@PathParam("id") String profileId) {
		return profileService.get(profileId);
	}

	@GET
	@Path("/{id}/exists")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Know if a Profile exists")
	public boolean checkProfileExists(@PathParam("id") String profileId) {
		return profileService.checkProfile(profileId);
	}


	@DELETE  //authenticate
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value= "Remove a Profile")
	public void removeProfileById(@PathParam("id") String id) { //authenticate

		profileService.removeProfile(id);
		profileProducer.sendProfileDeleted(id);
	}

	@PUT  //authenticate
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update a given profile")
	public void update(Profile profile) {
		profileService.update(profile);
	}

	@POST   //authenticate
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a new profile")
	public void create(Profile profile) {
		profileService.create(profile);
	}


	// Ingredients
	@GET   //authenticate
	@Path("{id}/ingredients")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the fridge content of a profile")
	public Set<Ingredient> getIngredient(@PathParam("id") String profileId){
		return profileService.get(profileId).getFridgeContents();
	}

	@POST  //authenticate
	@Path("{id}/ingredients")
	@ApiOperation(value ="add Ingredient")
	public void addIngredientById(@PathParam("id") String profileId, @QueryParam("ingredient") long ingredientId, @QueryParam("quantity") int quantity) {
		profileService.addIngredient(profileId, ingredientId, quantity);
	}
	 //authenticate
	@DELETE
	@Path("{id}/ingredients")
	@ApiOperation(value ="Remove Ingredient from fridge")
	public void removeIngredient(@PathParam("id") String profileId, @QueryParam("ingredient") long ingredientId) {
		profileService.removeIngredient(profileId,ingredientId);
	}

	 //authenticate
	// Favourites
	@GET
	@Path("{id}/favourites")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the favourites  of a profile")
	public Set<RecipeFav> getFavourite(@PathParam("id") String profileId){
		return profileService.get(profileId).getFavouriteRecipes();
	}
	 //authenticate
	@POST
	@Path("{id}/favourites")
	@ApiOperation(value ="add favourite")
	public void addFavouriteById(@PathParam("id") String profileId, @QueryParam("favourite") long favouriteId) {
		profileService.addFavourite(profileId, favouriteId);
	}
	 //authenticate
	@DELETE
	@Path("{id}/favourites")
	@ApiOperation(value ="Remove Favourite from fridge")
	public void removeFavourite(@PathParam("id") String profileId, @QueryParam("favourite") long favouriteId) {
		profileService.removeFavourite(profileId,favouriteId);

	}

}
