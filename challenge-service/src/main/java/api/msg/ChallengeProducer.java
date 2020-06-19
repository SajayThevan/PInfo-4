package api.msg;

import javax.enterprise.context.ApplicationScoped;
import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ChallengeProducer {

	@Producer
	private SimpleKafkaProducer<String, String> producer;

	public void sendAcceptRecipe(String recipeId) {
		log.info("Send that a solution have been validate");
		producer.send("challengeAccepted", recipeId);
	}
}
