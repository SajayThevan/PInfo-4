package domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import domain.model.Challenge;
import domain.model.ChallengeDTO;
import domain.model.Ingredient;
import domain.model.Recipe;



@ApplicationScoped
public class ChallengeServiceImpl implements ChallengeService {

    @PersistenceContext(unitName = "ChallengePU")
    private EntityManager em;

    @Override
    public ArrayList<ChallengeDTO> getChallengesForProfil(String id) {
    	TypedQuery<Challenge> query = em.createQuery("SELECT ch FROM Challenge ch WHERE ch.authorID = :authorID", Challenge.class);
		query.setParameter("authorID", id);
		List<Challenge> tmp = query.getResultList();
		ArrayList<ChallengeDTO> listToReturn = new ArrayList<>();
        Iterator<Challenge> it = tmp.iterator();
        while (it.hasNext()) {
        	Challenge ch = it.next();
        	ChallengeDTO chDTO = new ChallengeDTO(ch.getId(),ch.getName(),ch.getAuthorID(),ch.getIngredients());
        	listToReturn.add(chDTO);
        }
        return listToReturn;
    }

    @Override
	public List<Challenge> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Challenge> criteria = builder.createQuery(Challenge.class);
		criteria.from(Challenge.class);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	@Transactional
	public void update(Challenge challenge) {
		Challenge p = em.find(Challenge.class, challenge.getId());
		if (p == null) {
			throw new IllegalArgumentException("Challenge does not exist : " + challenge.getId());
		}
		em.merge(challenge);
	}

	@Override
	@Transactional
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
	@Transactional
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
	@Transactional
	public void removeChallenge(long id) {
		Challenge ch = get(id);
		em.remove(ch);
	}

	@Override
	public ArrayList<ChallengeDTO> getChallengesFromIngredientsIds(List<Long> ingIds) {
		ArrayList <ChallengeDTO> tr = new ArrayList<>();
		TypedQuery<Challenge> query = em.createQuery("SELECT c FROM Challenge c", Challenge.class);
		List<Challenge> cl = query.getResultList();
		for(Challenge c: cl ) {
			Set<Ingredient> ing = c.getIngredients();
			ArrayList<Long> containedIngId = new ArrayList<>();
			for(Ingredient i: ing) {
				containedIngId.add(i.getIngredientId());
			}

			if(containedIngId.containsAll(ingIds)){
				tr.add(new ChallengeDTO(c.getId(),c.getName(),c.getAuthorID(),c.getIngredients()));
			}
		}
		return tr;
	}

}
