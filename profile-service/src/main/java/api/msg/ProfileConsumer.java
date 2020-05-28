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

//	@Consumer(topics = "profilsReq", groupId = "pinfo-microservices")
//	public void updateProfile(final String message) {
//		log.info("Consumer got following message : " + message);
//		if ("all".equals(message)) {
//			producer.sendAllProfiles();
//		} else {
//			try {
//				String profileId = String.valueOf(message);  
//				producer.send(profileId);        
//			} catch(NumberFormatException e) {
//				throw new IllegalArgumentException("Message must be a String profile identifier ");
//			}
//		}
//	}
}
