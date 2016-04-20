package org.sgaonkar.webservices.messenger.resources;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.sgaonkar.webservices.messenger.model.Profile;
import org.sgaonkar.webservices.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

	ProfileService profilesService = new ProfileService();
	
	@GET
	public List<Profile> getProfiles(){
		return profilesService.getAllProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getMessage(@PathParam("profileName")String profileName){
		return profilesService.getProfile(profileName);
	}
	
	@POST
	public Profile addMessage(Profile profile){
		return profilesService.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateMessage(@PathParam("profileName")String profileName,Profile profile){
		profile.setProfileName(profileName);
		return profilesService.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public void removeMessage(@PathParam("profileName")String profileName){
		profilesService.removeProfile(profileName);
	}
}
