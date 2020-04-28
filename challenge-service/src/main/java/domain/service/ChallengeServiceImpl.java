package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import domain.model.Challenge;

@ApplicationScoped
public class ChallengeServiceImpl implements ChallengeService {

    @PersistenceContext(unitName = "ChallengePU")
    private EntityManager em;
    
    @Override				//The @Override annotation indicates that the child class method is over-writing its base class method.
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
	public Challenge get(Long challengeId) {
		System.out.println("-----------VOIR------------"+challengeId+"----------VOIR----------------");
		System.out.println("-----------VOIR------------"+em.find(Challenge.class, challengeId)+"----------VOIR----------------");
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
		System.out.println("-----------asdafsfaedgdsgf------------"+em.createQuery(cq).getSingleResult()+"----------asdasfsagR----------------");
		return em.createQuery(cq).getSingleResult();
	}
	
}
