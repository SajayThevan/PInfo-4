package api.msg;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;
import domain.model.Profile;
import domain.service.ProfileService;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ProfileProducer {
	
	@Producer
	private SimpleKafkaProducer<String, Profile> producer;

	@Inject
	private ProfileService profileService;
//
//	public void sendAllProfiles() {
//		log.info("Send the current state of ALL instruments to the topic");
//		for (Profile profile : profileService.getAll()) {
//			producer.send("profiles", profile);	 				
//		}
//	}
//
//	public void send(Profile profile) {
//		log.info("Send the state of an profile to the topic with id " + profile.getId() );
//		producer.send("profiles", profile);			
//	}

 			
	public void sendProfileDeleted(String profileId) {
		log.info("Send the state of an profile to the topic with id " + profileId);
		Profile profile = profileService.get(profileId);      
		if (profile != null) {								  
			producer.send("profilDelete",profile);
		}
	}
}




