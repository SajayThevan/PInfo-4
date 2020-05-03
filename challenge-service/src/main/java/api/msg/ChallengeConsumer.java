package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import lombok.extern.java.Log;


@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ChallengeConsumer {

	@Inject
	private ChallengeProducer producer;

	@Consumer(topics = "challengesReq", groupId = "pinfo-microservices")
	public void updateChallenge(final String message) {
		log.info("Consumer got following message : " + message);
		if ("all".equals(message)) {
			producer.sendAllChallenges();
		} else {
			try {
				Long challengeId = Long.valueOf(message);  
				producer.send(challengeId);        
			} catch(NumberFormatException e) {
				throw new IllegalArgumentException("Message must be wither a numeric challenge identifier or 'all'");
			}
		}
	}
}
