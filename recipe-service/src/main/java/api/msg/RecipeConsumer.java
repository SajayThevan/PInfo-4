package api.msg;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import lombok.extern.java.Log;
import domain.model.Recipe;
import domain.model.RecipeDTO;
import domain.service.RecipeService;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class RecipeConsumer {

	private RecipeService rs;
	
	@Consumer(topics = "profilDelete", groupId = "pinfo-microservices")
	public int deleteRecipe(final String message) {
		log.info("Consumer got following message : " + message); //Suppose message = author ID
		List<RecipeDTO> ids = rs.getRecipiesIdForProfiles(message);
		for (RecipeDTO rd: ids) {
			Recipe r = rs.getRecipe(rd.getId());
			rs.removeRecipe(rd.getId());
			r.setAuthorID("null");
			rs.addRecipe(r);
		}
		return 1;
	}
	
	@Consumer(topics = "challengeAccepted", groupId = "pinfo-microservices")
	public void challengeAccepted(final Recipe r) {
		rs.addRecipe(r);
	}
	
}
