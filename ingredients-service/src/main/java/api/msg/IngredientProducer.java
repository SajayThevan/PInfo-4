package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import domain.model.Ingredient;
import domain.service.IngredientService;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class IngredientProducer {

	@Producer
	private SimpleKafkaProducer<String, Ingredient> producer;

	@Inject
	private IngredientService ingredientService;
	
	public void sendAllIngredients() {
		log.info("Send the current state of ALL instruments to the topic");
		for (Ingredient ingredient : ingredientService.getAll()) {
			producer.send("instruments", ingredient);	
		}
	}

	public void send(Ingredient ingredient) {
		log.info("Send the state of an ingredient to the topic with id " + ingredient.getId() );
		producer.send("ingredients", ingredient);			
	}

	public void send(Long ingredientId) {
		log.info("Send the state of an instrument to the topic with id " + ingredientId);
		Ingredient ingredient = ingredientService.get(ingredientId);
		if (ingredient != null) {
			send(ingredient);
		}
	}
}