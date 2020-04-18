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
	
	@PersistenceContext(unitName = "InstrumentPU") // What is the unitName?
	private EntityManager em;

	public ProfileServiceImpl() {
	}

	public ProfileServiceImpl(EntityManager em) {
		this();
		this.em = em;
	}
	
	@Override				//The @Override annotation indicates that the child class method is over-writing its base class method.
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
			throw new IllegalArgumentException("Instrument does not exist : " + profile.getId());
		}
		em.merge(profile);
	}
	@Override							
	public Profile get(Long profileId) {
		return em.find(Profile.class, profileId);
	}

	@Override
	public void create(Profile profile) {
		if (profile.getId() != null) {
			throw new IllegalArgumentException("Instrument already exists : " + profile.getId());
		}
		em.persist(profile);
	}
	
	@Override
	public Long count() {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Profile.class)));
		return em.createQuery(cq).getSingleResult();
	}
	
	
	
	
	

	
// I let those in comment no to be forced to see it on github -> me speak very good English language

//	@Override
//	public List<Instrument> getAll() {
//		CriteriaBuilder builder = em.getCriteriaBuilder();
//		CriteriaQuery<Instrument> criteria = builder.createQuery(Instrument.class);
//		criteria.from(Instrument.class);
//		return em.createQuery(criteria).getResultList();
//	}
//
//	@Override
//	public void update(Instrument instrument) {
//		Instrument i = em.find(Instrument.class, instrument.getId());
//		if (i == null) {
//			throw new IllegalArgumentException("Instrument does not exist : " + instrument.getId());
//		}
//		em.merge(instrument);
//	}
//
//	@Override
//	public Instrument get(Long instrumentId) {
//		return em.find(Instrument.class, instrumentId);
//	}
//
//	@Override
//	public void create(Instrument instrument) {
//		if (instrument.getId() != null) {
//			throw new IllegalArgumentException("Instrument already exists : " + instrument.getId());
//		}
//		em.persist(instrument);
//	}
//	
//	@Override
//	public Long count() {
//		CriteriaBuilder qb = em.getCriteriaBuilder();
//		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
//		cq.select(qb.count(cq.from(Instrument.class)));
//		return em.createQuery(cq).getSingleResult();
//	}
}
