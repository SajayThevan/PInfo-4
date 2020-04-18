package domain.service;

//TODO: import.model.<attribut>

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class RecipesServiceImplTest {

	@Spy
	@PersistenceContext(unitName = "RecipesPUTest")
	EntityManager em;

	@InjectMocks
	private RecipeServiceImpl recipesService;

//	@Test
//	void testGetAll() {
//		List<Instrument> instruments = instrumentService.getAll();
//		int size = instruments.size();
//		
//		instrumentService.create(getRandomInstrument());
//		instrumentService.create(getRandomInstrument());
//		instrumentService.create(getRandomInstrument());
//		instrumentService.create(getRandomInstrument());
//		
//		assertEquals(size + 4, instrumentService.getAll().size());
//	}
}
