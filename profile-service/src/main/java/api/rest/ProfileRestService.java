package api.rest;



import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import api.msg.ProfileProducer;
import domain.model.Profile;
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
		System.out.println("-----------------LONG COUNT:"+profileService.count()+"-----------------");
		
		return profileService.count();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get a specifc profile")
	public Profile get(@PathParam("id") Long profileId) {
		
		return profileService.get(profileId);
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

	
	@POST
	@Path("propagateAllProfiles")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Propagate all profiles to the bus to sync up downstream services")
	public void propagateAllProfiles() {
		profileProducer.sendAllProfiles();
	}
}