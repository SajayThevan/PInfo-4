package api.msg;

import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;

import domain.model.Recipe;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class RecipeProducer {

	@Producer
	private SimpleKafkaProducer<String, ArrayList<Object>> producer;
	
	public void sendRecipeAdded(Recipe r) {
		log.info("Send that a recipe has been added");
		ArrayList<Object> toSend = new ArrayList<>();
		toSend.add(r.getAuthorID());
		toSend.add(r.getId());
		producer.send("Recipe added",toSend);
	}
	

}
