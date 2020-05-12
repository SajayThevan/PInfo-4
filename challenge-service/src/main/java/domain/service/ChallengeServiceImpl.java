package domain.service;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import domain.model.Challenge;
import domain.model.Recipe;

@ApplicationScoped
public class ChallengeServiceImpl implements ChallengeService {

    @PersistenceContext(unitName = "ChallengePU")
    private EntityManager em;
    
    @Override				
	public List<Challenge> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Challenge> criteria = builder.createQuery(Challenge.class);
		criteria.from(Challenge.class);
		return em.createQuery(criteria).getResultList();
	}
	
	@Override
	public void update(Challenge challenge) {
		Challenge p = em.find(Challenge.class, challenge.getId());
		if (p == null) {
			throw new IllegalArgumentException("Challenge does not exist : " + challenge.getId());
		}
		em.merge(challenge);
	}
	
	@Override
	public void addSolution(long id, long recipeId) {
		Challenge ch = get(id);
		Recipe r = new Recipe();
		r.setRecipeId(recipeId);
		Set <Recipe> oldSolution = ch.getSolutions();
		oldSolution.add(r);
		em.merge(ch);
		
	}
	@Override							
	public Challenge get(Long challengeId) {
		return em.find(Challenge.class, challengeId);
	}

	@Override
	public void create(Challenge challenge) {
		if (challenge.getId() != null) {
			throw new IllegalArgumentException("Challenge already exists : " + challenge.getId());
		}
		em.persist(challenge);
	}
	
	@Override
	public Long count() {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Challenge.class)));
		return em.createQuery(cq).getSingleResult();
	}
	
	@Override
	public void removeChallenge(long id) {
		Challenge ch = get(id);
		em.remove(ch);
	}
	
}
