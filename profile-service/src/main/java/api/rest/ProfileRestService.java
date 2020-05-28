package api.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
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
	
	private String publicKeyString;

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

	// Profile
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a specific profile")
	public Profile get(@PathParam("id") String profileId, @HeaderParam("Authorization") String auth) {
		if (!authenticate(profileId,auth)){
			throw new WebApplicationException("Unauthorized", Response.status(Status.UNAUTHORIZED).build());
		}
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

	public boolean authenticate(String profileId ,String auth) {
		String token = auth.substring(7); // remove 'Bearer ' from 'Bearer ${token}'
				
		// TODO: Write tests for this function!!!
		// TODO: Can we assign this key to an attribute of the class and reuse it across calls?????
		
		// Obtain public key
		if (publicKeyString == null) {
		    try {
				String url = "https://pinfo4.unige.ch/auth/realms/apigw";
				HttpClient client = HttpClients.createDefault();
				HttpGet request = new HttpGet(url);
			    HttpResponse response = client.execute(request);
			    String json = EntityUtils.toString(response.getEntity());
			    JSONObject realm = new JSONObject(json);
			    publicKeyString = realm.getString("public_key");
		    } catch (IOException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }  
	    }
	   
		// Get public key from string
			// c.f. https://gist.github.com/destan/b708d11bd4f403506d6d5bb5fe6a82c5
		KeyFactory kf = null;
		try {
			kf = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString));
        RSAPublicKey pubKey = null;
		try {
			pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
			
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Verify Token
		DecodedJWT jwt = JWT.decode(token);
		try {
		    Algorithm algorithm = Algorithm.RSA256(pubKey,null);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("https://pinfo4.unige.ch/auth/realms/apigw")
		        .build(); //Reusable verifier instance
		    verifier.verify(token);
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
			return false;
		}
		String userId = jwt.getSubject();
		
		if ((profileId == null) || (userId.contentEquals(profileId) )) {
			return true;
		}
		return false;	
	}

}
