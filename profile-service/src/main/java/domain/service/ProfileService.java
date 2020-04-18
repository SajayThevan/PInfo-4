package domain.service;

import java.util.List;

import domain.model.Profile;

public interface ProfileService {
	List<Profile> getAll();
	void update(Profile profile);
	void create(Profile profile);
	Profile get(Long profileId);
	Long count();
	
}