package api.msg;

import domain.model.CategoryEnum;
import domain.model.Recipe;
import domain.service.RecipeService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class RecipeProducerTest {
	
	@SuppressWarnings("rawtypes")
	@Mock
	private SimpleKafkaProducer<String, ArrayList> kafkaProducer;
	@Mock
	private RecipeService rs;

	@InjectMocks
	private RecipeProducer producer;

	@Test
	void testSendRecipeAdded() {
		Recipe r = randomRecipe();
		producer.sendRecipeAdded(r);
		ArrayList ts = new ArrayList();
		ts.add(r.getAuthorID());
		ts.add(r.getId());
		verify(kafkaProducer, times(1)).send("Recipe added", ts);
	}


	public Recipe randomRecipe() {
		Recipe r = new Recipe();
		List<String> co = new ArrayList<String>();
		co.add("TG");
		List<Long> ing = new ArrayList<Long>();
		ing.add((long)2);
		List<String> step = new ArrayList<String>();
		step.add("Tu mets dans le four et tg");
		List<Integer> rating = new ArrayList<Integer>();
		rating.add(5);
		List cat = new ArrayList<CategoryEnum>();
		cat.add(CategoryEnum.Dinner);
		r.setAuthorID((long) new Random().nextInt(9999 + 1)+1); // Set the profilID between 1 et 10000
		r.setDate("Demain");
		r.setDifficulty(new Random().nextInt(10 + 1)+1); // Set the difficulty between 1 et 10
		r.setName("Pizza");
		r.setTime(2);
		r.setSteps(step);
		r.setRatings(rating);
		r.setCategory(cat);
		r.setIngredients(ing);
		r.setComments(co);
		
		return r;
	}
	
	

	/*
	  
	 ********************* Keep for test examplt ************* 
	 
	
	@Test
	void testSendInstrument() {
		Instrument instrument = getRandomInstrument();
		producer.send(instrument);
		verify(kafkaProducer, times(1)).send("instruments", instrument);
	}

	@Test
	void testSendLong() {
		Instrument instrument = getRandomInstrument();
		when(instrumentService.get(instrument.getId())).thenReturn(instrument);
		producer.send(instrument.getId());
		verify(kafkaProducer, times(1)).send("instruments", instrument);
	}

	@Test
	void testSendLongNull() {
		Instrument instrument = getRandomInstrument();
		when(instrumentService.get(instrument.getId())).thenReturn(null);
		producer.send(instrument.getId());
		verify(kafkaProducer, times(0)).send("instruments", instrument);
	}

	private List<Instrument> getRandomInstrumentCollection() {
		List<Instrument> instruments = new ArrayList<>();
		long numberOfInstrument = Math.round((Math.random() * 1000));
		for (int i = 0; i < numberOfInstrument; i++) {
			instruments.add(getRandomInstrument());
		}
		return instruments;
	}

	private Instrument getRandomInstrument() {
		Bond b = new Bond();
		b.setBrokerLei(UUID.randomUUID().toString());
		b.setCounterpartyLei(UUID.randomUUID().toString());
		b.setAmountInOriginalCurrency(new BigDecimal("0.0"));
		b.setOriginalCurrency("USD");
		b.setValueDate(new Date());
		b.setMaturityDate(new Date());
		b.setIsin(UUID.randomUUID().toString());
		b.setQuantity(Long.valueOf(Math.round(Math.random() * 1000)));
		return b;
	}
	
	*/

	
}
