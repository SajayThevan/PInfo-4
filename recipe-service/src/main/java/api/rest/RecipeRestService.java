package api.rest;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.model.Recipe;
import domain.model.RecipeDTO;
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

	@GET
	@Path("testVolume")
	@ApiOperation(value = "test volume")
	public void RecipeTestVolume() {
		rs.RecipeTestVolume();
	}

	// Challenges
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a full Recipe")
	public Recipe getRecipeRest(@PathParam("id") long id) {
		Recipe a = rs.getRecipe(id);
		return a;
	}
	 //authenticate check if user
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a new Recipe",  notes = "Create a new Recip.")
	public void createRecipe(Recipe r) {
		rs.addRecipe(r);
	}
	 //authenticate
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value= "Remove a Recipe")
	public void removeRecipeRest(@PathParam("id") long id) {
		rs.removeRecipe(id);
	}


	// Get Recipes
	// Get recipes from Ingredient ID's
	@GET
	@Path("/ingredients/")
	@Produces(MediaType.APPLICATION_JSON) // ?id=5&?id=6&id=5&?id=6&
	@ApiOperation(value = "Get recipes which you need the ingredients passed in paramaters")
	public ArrayList<RecipeDTO> getRecipesWithIngIds(@QueryParam("id") ArrayList<Long> IngredientIdList){
		return rs.getRecipeWithIngredientID(IngredientIdList);
	}

	// Get list of recipes
	@GET
	@Produces(MediaType.APPLICATION_JSON) // ?id=5&?id=6&id=5&?id=6&
	@ApiOperation(value = "Get recipes which you need the ingredients passed in paramaters")
	public ArrayList<RecipeDTO> getRecipesWithIdList(@QueryParam("id") ArrayList<Long> idList){
		// Return list of recipes from id's
		return rs.getRecipesListFromIds(idList);
	}

	// Get recipes for profile
	//authenticate
	@GET
	@Path("/profiles/{profileID}") // TODO: Not the best uri??
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value ="Get Recipes for profil ID")
	public ArrayList<RecipeDTO> getRecipesForProfilRest(@PathParam("profileID") String id) {
		return rs.getRecipesForProfil(id);
	}

	@GET
	@Path("/trends")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "return the 20 best recipes")
	public ArrayList<RecipeDTO> getTendancies(){
		return rs.getTendancies();
	}

	@GET
	@Path("/recipe-of-the-month")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "return the best recipe this month")
	public ArrayList<RecipeDTO> getRecipeOfTheMonth(){
		return rs.getRecipeOfTheMonth();
	}

	 //authenticate
	// Put attributes
	@PUT
	@Path("{id}/rate")
	@ApiOperation(value ="Update recipe ratings")
	public void addRates(@PathParam("id") long id, @QueryParam("rate")int rate) {
		rs.addRating(id,rate);
	}
	 //authenticate
	@PUT
	@Path("{id}/comments")
	@Consumes(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Add a comment")
	public void addCommentRest(@PathParam("id") long id, String comment) {
		rs.addComment(comment,id);

	}

	// Count
	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a the count of recipe")
  public Long count() {
		return rs.count();
	}

	@GET
	@Path("/image/{id}")
	@Produces("image/jpg")
	public Response getFile(@PathParam("id") String id) throws SQLException{
		File file = new File("tmp/image/recipe/"+id+".jpg");
		return Response.ok(file, "image/jpg").header("Inline", "filename=\"" + file.getName() + "\"")
				.build();
	}

	@POST
	@Path("/image/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("image/jpg")
	public String uploadImage(@FormParam("image") String image, @PathParam("id") String id) throws SQLException, FileNotFoundException
	{
		String result = "false";
		FileOutputStream fos;
		fos = new FileOutputStream("tmp/image/recipe/"+id+".jpg");
		// decode Base64 String to image
		try
		{
			byte byteArray[] = Base64.getMimeDecoder().decode(image);
			fos.write(byteArray);

			result = "true";
			fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}



}
