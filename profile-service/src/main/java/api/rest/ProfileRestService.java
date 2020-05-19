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
@Path("/profile")
@Api(value = "profile", authorizations = {
	      @Authorization(value="sampleoauth", scopes = {})
	    })
public class ProfileRestService {

	@Inject
	private ProfileService profileService;
	@Inject
	private ProfileProducer profileProducer;

	@GET
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

	// Profile
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a specifc profile")
	public Profile get(@PathParam("id") Long profileId) {
		return profileService.get(profileId);
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value= "Remove a Profile")
	public void removeProfileById(@PathParam("id") long id) {
		profileService.removeProfile(id);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update a given profile")
	public void update(Profile profile) {
		profileService.update(profile);
		profileProducer.send(profile);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a new profile")
	public void create(Profile profile) {
		profileService.create(profile);
		profileProducer.send(profile);
	}


	// Ingredients
	@GET
	@Path("{id}/ingredients")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the fridge content of a profile")
	public Set<Ingredient> getIngredient(@PathParam("id") Long profileId){
		return profileService.get(profileId).getFridgeContents();
	}

	@POST
	@Path("{id}/ingredients")
	@ApiOperation(value ="add Ingredient")
	public void addIngredientById(@PathParam("id") long profileId, @QueryParam("ingredient") long ingredientId, @QueryParam("quantity") int quantity) {
		profileService.addIngredient(profileId, ingredientId, quantity);
	}

	@DELETE
	@Path("{id}/ingredients")
	@ApiOperation(value ="Remove Ingredient from fridge")
	public void removeIngredient(@PathParam("id") long profileId, @QueryParam("ingredient") long ingredientId) {
		profileService.removeIngredient(profileId,ingredientId);
	}


	// Favourites
	@GET
	@Path("{id}/favourites")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the favourites  of a profile")
	public Set<RecipeFav> getFavourite(@PathParam("id") Long profileId){
		return profileService.get(profileId).getFavouriteRecipes();
	}

	@POST
	@Path("{id}/favourites")
	@ApiOperation(value ="add favourite")
	public void addFavouriteById(@PathParam("id") long profileId, @QueryParam("favourite") long favouriteId) {
		profileService.addFavourite(profileId, favouriteId);
	}

	@DELETE
	@Path("{id}/favourites")
	@ApiOperation(value ="Remove Favourite from fridge")
	public void removeFavourite(@PathParam("id") long profileId, @QueryParam("favourite") long favouriteId) {
		profileService.removeFavourite(profileId,favouriteId);
	}


	// TODO: Delete??
	@POST
	@Path("propagateAllProfiles")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Propagate all profiles to the bus to sync up downstream services")
	public void propagateAllProfiles() {
		profileProducer.sendAllProfiles();
	}
}
