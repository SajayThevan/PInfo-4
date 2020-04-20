package api.msg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.javatuples.Triplet; 


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import lombok.extern.java.Log;
import domain.model.Recipe;
import domain.service.RecipeService;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class RecipeConsumer {

	@Inject
	private RecipeProducer producer;

	private RecipeService rs;

	
	@Consumer(topics = "profilDelete", groupId = "pinfo-microservices")
	public void deleteRecipe(final long message) {
		log.info("Consumer got following message : " + message); //Suppose message = author ID
		List ids = rs.getRecipiesIdForProfiles(message);
		for (int i=0; i < ids.size(); i++) {
			long recipeID = (long) ids.get(i);
			rs.removeRecipe(recipeID);
		}
	}
	
	@Consumer(topics = "challengeAccepted", groupId = "pinfo-microservices")
	public void challengeAccepted(final Recipe r) {
		rs.addRecipe(r);
	}
	
}
