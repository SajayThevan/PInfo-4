package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;
import domain.model.Challenge;
import domain.service.ChallengeService;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ChallengeProducer {
	
	@Producer
	private SimpleKafkaProducer<String, Challenge> producer;

	@Inject
	private ChallengeService challengeService;

	public void sendAllChallenges() {
		log.info("Send the current state of ALL challenge to the topic");
		for (Challenge challenge : challengeService.getAll()) {
			producer.send("challenges", challenge);	 				
		}
	}

	public void send(Challenge challenge) {
		log.info("Send the state of an challenge to the topic with id " + challenge.getId() );
		producer.send("challenges", challenge);			
	}

 			
	public void send(Long challengeId) {
		log.info("Send the state of an challenge to the topic with id " + challengeId);
		Challenge challenge = challengeService.get(challengeId);      
		if (challenge != null) {								  
			send(challenge);
		}
	}
}




