package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import lombok.extern.java.Log;



@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ProfileConsumer {

	@Inject
	private ProfileProducer producer;

	@Consumer(topics = "profilsReq", groupId = "pinfo-microservices")
	public void updateProfile(final String message) {
		log.info("Consumer got following message : " + message);
		if ("all".equals(message)) {
			producer.sendAllProfiles();
		} else {
			// interpret the instrument id
			try {
				//Long profileId = Long.valueOf(message);  // Error due to the function "send(profileId)" defined in ProfileProducer.java waiting for the database issue.
				//producer.send(profileId);        
			} catch(NumberFormatException e) {
				throw new IllegalArgumentException("Message must be wither a numeric profile identifier or 'all'");
			}
		}
	}
}
