package api.msg;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;

import domain.model.ChallengeDTO;
import domain.service.ChallengeService;
import lombok.extern.java.Log;


@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ChallengeConsumer {
	
	@Inject
	private ChallengeService challengeService;

	@Consumer(topics = "profilDelete", groupId = "pinfo-microservices")
	public void deleteChallenge(final String message) {
		log.info("Consumer got following message : " + message);
		List<ChallengeDTO> ids = challengeService.getChallengesForProfil(message);
		for (ChallengeDTO cDTO: ids) {
			challengeService.removeChallenge(cDTO.getId());
			//ch.setAuthorID("null");
		}

	}
}
