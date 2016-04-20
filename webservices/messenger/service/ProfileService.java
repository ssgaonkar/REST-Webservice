package org.sgaonkar.webservices.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sgaonkar.webservices.messenger.database.DatabaseClass;
import org.sgaonkar.webservices.messenger.model.Profile;;

public class ProfileService {
	private static Map<String,Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService(){
		profiles.put("sgaonkar", new Profile(1,"Suman","Gaonkar","sgaonkar"));
		profiles.put("sgaonkar", new Profile(2,"Suman","Gaonkar","sgaonkar"));
	}
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getId()<=0){
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public void removeProfile(String ProfileName){
		 profiles.remove(ProfileName);
	}

}
