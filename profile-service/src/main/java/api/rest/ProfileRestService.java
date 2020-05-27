package api.rest;

//import javax.ws.rs.core.HttpHeaders;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.exceptions.JWTDecodeException;
//import com.auth0.jwt.interfaces.Claim;
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


	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value= "Remove a Profile")
	public void removeProfileById(@PathParam("id") String id) {
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
	@Consumes(MediaType.APPLICATION_JSON)
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
	public Set<Ingredient> getIngredient(@PathParam("id") String profileId){
		return profileService.get(profileId).getFridgeContents();
	}

	@POST
	@Path("{id}/ingredients")
	@ApiOperation(value ="add Ingredient")
	public void addIngredientById(@PathParam("id") String profileId, @QueryParam("ingredient") long ingredientId, @QueryParam("quantity") int quantity) {
		profileService.addIngredient(profileId, ingredientId, quantity);
	}

	@DELETE
	@Path("{id}/ingredients")
	@ApiOperation(value ="Remove Ingredient from fridge")
	public void removeIngredient(@PathParam("id") String profileId, @QueryParam("ingredient") long ingredientId) {
		profileService.removeIngredient(profileId,ingredientId);
	}


	// Favourites
	@GET
	@Path("{id}/favourites")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the favourites  of a profile")
	public Set<RecipeFav> getFavourite(@PathParam("id") String profileId){
		return profileService.get(profileId).getFavouriteRecipes();
	}

	@POST
	@Path("{id}/favourites")
	@ApiOperation(value ="add favourite")
	public void addFavouriteById(@PathParam("id") String profileId, @QueryParam("favourite") long favouriteId) {
		profileService.addFavourite(profileId, favouriteId);
	}

	@DELETE
	@Path("{id}/favourites")
	@ApiOperation(value ="Remove Favourite from fridge")
	public void removeFavourite(@PathParam("id") String profileId, @QueryParam("favourite") long favouriteId) {
		profileService.removeFavourite(profileId,favouriteId);
	}

	public boolean authenticate(String profileId ,String auth) {
		String token = auth.substring(7); // substring to remove 'Bearer '
		
		// TODO: Write tests for this function, not working and would be best to test with
		
		// TODO: Can we assign this key to an attribute of the class and reuse it across calls?????
		// TODO: Get the key instance dynamically
		// c.f. https://gist.github.com/destan/b708d11bd4f403506d6d5bb5fe6a82c5
//		String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDaj2mWokUVRg1dwgOjIQZGiLCFkVWhHxeAO5TJxPIuvoAxNnkYEBvY/6QCDCn1m2EcLcRKoZuyTeiP5l/XRMHIfp3K8mI0w6tzMk/eDsFIrOl7eE2anV52/O2WoVr6j5X1eOZAzsCvROzou/u3eMa+D15FkHgPwwRP4A0Mj1cemQIDAQAB";
//		KeyFactory kf = null;
//		try {
//			kf = KeyFactory.getInstance("RSA");
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString));
//        RSAPublicKey pubKey = null;
//		try {
//			pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
//		} catch (InvalidKeySpecException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		DecodedJWT jwt = null;
//		try {
//		    Algorithm algorithm = Algorithm.RSA256(pubKey, null);
//		    JWTVerifier verifier = JWT.require(algorithm)
//		        .withIssuer("auth0")
//		    .build(); //Reusable verifier instance
//		    jwt = verifier.verify(token);
//		} catch (JWTVerificationException exception){
//		    //Invalid signature/claims
//			return false;
//		}
		
		
		DecodedJWT jwt = JWT.decode(token);
		
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(jwt);
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		String userId = jwt.getSubject();
		System.out.println(userId);
		System.out.println(profileId);
		if (userId == profileId) {
			return true;
		}
		return false;
	}

}
