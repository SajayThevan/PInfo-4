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

//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONObject;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.auth0.jwt.interfaces.JWTVerifier;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Set;


import domain.model.Challenge;
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
