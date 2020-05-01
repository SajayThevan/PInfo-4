package domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import domain.model.Challenge;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)


class ChallengeServiceImplTest {

	@Spy
	@PersistenceContext(unitName = "ChallengePUTest")
	EntityManager em;

	@InjectMocks
	private ChallengeServiceImpl challengeService;

	void testGetAll() {
		System.out.println("-----------------DEBUT TEST GETALL-----------------");
		List<Challenge> challenges = challengeService.getAll();
		int size = challenges.size();
		
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		
		assertEquals(size + 4, challengeService.getAll().size());
		System.out.println("-----------------TEST GETALL TERMINE-----------------");
	}

	@Test
	void testCount() {
		System.out.println("-----------------DEBUT TEST COUNT-----------------");
		List<Challenge> challenges = challengeService.getAll();
		int size = challenges.size();
		
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		challengeService.create(getRandomChallenge());
		
		Long count = challengeService.count();
		assertEquals(size + 4, count);
		System.out.println("-----------------TEST COUNT TERMINE-----------------");
	}
	
//	@Test
//	void testUpdate() {
//		System.out.println("-----------------DEBUT TEST UPDATE-----------------");
//		challengeService.create(getRandomChallenge());
//		Challenge challenge = challengeService.getAll().get(0);
//		assertNotNull(challenge);
//		Long id = challenge.getId();
//		challenge.setFirst_name("Deniz");
//		challengeService.update(challenge);
//		challenge = challengeService.get(id);
//		assertEquals("Deniz", challenge.getFirst_name());
//		System.out.println("-----------------TEST UPDATE TERMINE-----------------");
//	}
	
	@SuppressWarnings("serial")
	@Test
	void testUpdateNonExistant() {
		System.out.println("-----------------DEBUT TEST UPDATE NON EXISTANT-----------------");
		Challenge i = new Challenge() {
			@Override
			public Long getId() {
				return Long.MAX_VALUE;
			}
		};
		assertThrows(IllegalArgumentException.class, () -> {
			challengeService.update(i);
		});
		System.out.println("-----------------TEST UPDATE NON EXISTANT TERMINE-----------------");
	}

//	@Test
//	void testGet() {
//		System.out.println("-----------------DEBUT TEST GET-----------------");
//		challengeService.create(getRandomChallenge());
//		Challenge challenge = challengeService.getAll().get(0);
//		assertNotNull(challenge);
//		Long id = challenge.getId();
//		Challenge getChallenge = challengeService.get(id);
//		System.out.println("---------------------"+challenge.getFridge_contents()+"----------------");
//		assertEquals(challenge.getFirst_name(), getChallenge.getFirst_name());     
//		System.out.println("-----------------TEST GET TERMINE-----------------");
//	}

	@Test
	void testGetNonExistant() {
		System.out.println("-----------------DEBUT TEST GET NON EXISTANT-----------------");
		List<Challenge> challenges = challengeService.getAll();
		System.out.println("testGetNonExistant:" + challenges.size());

		assertNull(challengeService.get(Long.MAX_VALUE));
		System.out.println("-----------------TEST GET NON EXISTANT TERMINE-----------------");
	}

	@Test
	void testCreate() {
		System.out.println("-----------------DEBUT TEST CREATION PROFILE-----------------");
		Challenge challenge = getRandomChallenge();
		challengeService.create(challenge);
		assertNotNull(challenge.getId());
		System.out.println("-----------------TEST CREATION PROFILE TERMINE-----------------");
	}


	@Test
	void testCreateDuplicate() {
		System.out.println("-----------------DEBUT TEST CREATION DUPLICAT-----------------");
		Challenge challenge = getRandomChallenge();
		challengeService.create(challenge);
		assertThrows(IllegalArgumentException.class, () -> {
			challengeService.create(challenge);
		});
		System.out.println("-----------------TEST CREATION DUPLICAT TERMINE-----------------");
	}

	private Challenge getRandomChallenge() {
		Challenge c = new Challenge();


		return c;
	}
}
