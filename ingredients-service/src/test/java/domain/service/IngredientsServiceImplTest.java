package domain.service;

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

import domain.model.Bond;
import domain.model.Ingredient;
import eu.drus.jpa.unit.api.JpaUnit;

@ExtendWith(JpaUnit.class)
@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

	@Spy
	@PersistenceContext(unitName = "IngredientPUTest")
	EntityManager em;

	@InjectMocks
	private IngredientServiceImpl ingredientService;
	
	@Test
	void testGet() {
		ingredientService.create(getRandomIngredient());
		Ingredient ingredient = ingredientService.getAll().get(0);
		assertNotNull(ingredient);
		Long id = ingredient.getId();
		Ingredient getInstrument = ingredientService.get(id);
		assertEquals(ingredient.getName(), getInstrument.getName());
	}

	@Test
	void testGetNonExistant() {
		List<Ingredient> ingredients = ingredientService.getAll();
		System.out.println("testGetNonExistant:" + ingredients.size());

		assertNull(ingredientService.get(Long.MAX_VALUE));
	}

	private Ingredient getRandomIngredient() {
		Bond b = new Bond();
		b.setName(UUID.randomUUID().toString());
		b.setKcal((int) Math.round(Math.random()*1000));
		b.setFat((int) Math.round(Math.random()*1000));
		b.setCholesterol((int) Math.round(Math.random()*1000));
		b.setProtein((int) Math.round(Math.random()*1000));
		b.setCholesterol((int) Math.round(Math.random()*1000));
		b.setSalt((int) Math.round(Math.random()*1000));
		b.setMaturityDate(new Date());
		b.setIsin(UUID.randomUUID().toString());
		b.setQuantity(Long.valueOf(Math.round(Math.random()*1000)));
		return b;
	}
}