package domain.service;

import java.util.List;

import domain.model.Challenge;


public interface ChallengeService {

	List<Challenge> getAll();
	void update(Challenge challenge);
	void create(Challenge challenge);
	Challenge get(Long challengeId);
	Long count();

}