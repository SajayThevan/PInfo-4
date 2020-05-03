package domain.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import domain.model.Profile;

@ApplicationScoped
public class ProfileServiceImpl implements ProfileService {
	
	@PersistenceContext(unitName = "ProfilePU") 
	private EntityManager em;

	public ProfileServiceImpl() {
	}

	public ProfileServiceImpl(EntityManager em) {
		this();
		this.em = em;
	}
	
	@Override				
	public List<Profile> getAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Profile> criteria = builder.createQuery(Profile.class);
		criteria.from(Profile.class);
		return em.createQuery(criteria).getResultList();
	}
	
	@Override
	public void update(Profile profile) {
		Profile p = em.find(Profile.class, profile.getId());
		if (p == null) {
			throw new IllegalArgumentException("Profile does not exist : " + profile.getId());
		}
		em.merge(profile);
	}
	@Override							
	public Profile get(Long profileId) {
		System.out.println("-----------VOIR------------"+profileId+"----------VOIR----------------");
		System.out.println("-----------VOIR------------"+em.find(Profile.class, profileId)+"----------VOIR----------------");
		return em.find(Profile.class, profileId);
	}

	@Override
	public void create(Profile profile) {
		if (profile.getId() != null) {
			throw new IllegalArgumentException("Profile already exists : " + profile.getId());
		}
		em.persist(profile);
	}
	
	@Override
	public Long count() {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Profile.class)));
		System.out.println("-----------asdafsfaedgdsgf------------"+em.createQuery(cq).getSingleResult()+"----------asdasfsagR----------------");
		return em.createQuery(cq).getSingleResult();
	}
	
	
}
