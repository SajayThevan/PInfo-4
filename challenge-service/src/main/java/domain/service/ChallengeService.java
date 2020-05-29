package domain.service;

import java.util.ArrayList;
import java.util.List;

import domain.model.Challenge;
import domain.model.ChallengeDTO;


public interface ChallengeService {
	List<Challenge> getAll();
	void update(Challenge challenge);
	void create(Challenge challenge);
	Challenge get(Long challengeId);
	Long count();
	void addSolution(long id, long recipeId);
	void removeChallenge(long id);
	ArrayList<ChallengeDTO> getChallengesForProfil(String id);
	ArrayList<ChallengeDTO> getChallengesFromIngredientsIds(ArrayList<Long> ingIds);

}