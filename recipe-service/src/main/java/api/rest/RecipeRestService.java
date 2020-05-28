package api.rest;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;

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

//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.javatuples.Triplet;
//import org.json.JSONObject;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.auth0.jwt.interfaces.JWTVerifier;

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
	public ArrayList<RecipeDTO> getTendanciess(){
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
	@ApiOperation(value = "Get a the count of instrument")
  public Long count() {
		return rs.count();
	}
	
//	public boolean authenticate(String profileId ,String auth) {
//		String token = auth.substring(7); // substring to remove 'Bearer '
//				
//		// TODO: Write tests for this function, not working and would be best to test with
//		// TODO: Can we assign this key to an attribute of the class and reuse it across calls?????
//		// TODO: Get the key instance dynamically
//		
//		// c.f. https://gist.github.com/destan/b708d11bd4f403506d6d5bb5fe6a82c5
//		if (publicKeyString == null) {
//
//		    try {
//				String url = "https://pinfo4.unige.ch/auth/realms/apigw";
//				HttpClient client = HttpClients.createDefault();
//				HttpGet request = new HttpGet(url);
//			    HttpResponse response = client.execute(request);
//			    String json = EntityUtils.toString(response.getEntity());
//			    JSONObject realm = new JSONObject(json);
//			    publicKeyString = realm.getString("public_key");
//		    }
//	
//		    catch (IOException e ) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//		    	
//		    }  
//	    }
//	   
//	    
//	    
//		
//		//String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDaj2mWokUVRg1dwgOjIQZGiLCFkVWhHxeAO5TJxPIuvoAxNnkYEBvY/6QCDCn1m2EcLcRKoZuyTeiP5l/XRMHIfp3K8mI0w6tzMk/eDsFIrOl7eE2anV52/O2WoVr6j5X1eOZAzsCvROzou/u3eMa+D15FkHgPwwRP4A0Mj1cemQIDAQAB";
//		KeyFactory kf = null;
//		try {
//			kf = KeyFactory.getInstance("RSA");
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString));
//        RSAPublicKey pubKey = null;
//		try {
//			pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
//			
//		} catch (InvalidKeySpecException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		DecodedJWT jwt = JWT.decode(token);
//		try {
//		    Algorithm algorithm = Algorithm.RSA256(pubKey,null);
//		    JWTVerifier verifier = JWT.require(algorithm)
//		        .withIssuer("https://pinfo4.unige.ch/auth/realms/apigw")
//		        .build(); //Reusable verifier instance
//		    verifier.verify(token);
//		} catch (JWTVerificationException exception){
//		    //Invalid signature/claims
//			return false;
//		}
//		String userId = jwt.getSubject();
//		
//		if ((profileId == null) || (userId.contentEquals(profileId) )) {
//			return true;
//		}
//		return false;	
//	}

}
