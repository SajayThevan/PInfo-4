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

	public void sendAllProfiles() {
		log.info("Send the current state of ALL instruments to the topic");
		for (Profile profile : profileService.getAll()) {
			producer.send("profiles", profile);	 				//send is a kafka's method
		}
	}

	public void send(Profile profile) {
		log.info("Send the state of an profile to the topic with id " + profile.getId() );
		producer.send("profiles", profile);			
	}

// 			IN COMMENT FOR THE MOMENT BUT USEFUL
//	public void send(Long profileId) {
//		log.info("Send the state of an profile to the topic with id " + profileId);
//		Profile profile = ProfileService.get(profileId);       // Error: I have to modify ProfileService.java get(Long profileId) to static ? (It add a body to the method get in profileService)
//		if (profile != null) {								  //  I think the error is due to the way we define the dataBase at the moment, we should not fix the error quickly as suggested.
//			send(profile);
//		}
//	}
}




// I let those in comment no to be forced to see it on github -> me speak very good English language

//public void sendAllInstruments() {
//	log.info("Send the current state of ALL instruments to the topic");
//	for (Profile profile : instrumentService.getAll()) {
//		producer.send("instruments", profile);	
//	}
//}
//
//public void send(Profile profile) {
//	log.info("Send the state of an profile to the topic with id " + profile.getId() );
//	producer.send("instruments", profile);			
//}
//
//public void send(Long instrumentId) {
//	log.info("Send the state of an profile to the topic with id " + instrumentId);
//	Profile profile = instrumentService.get(instrumentId);
//	if (profile != null) {
//		send(profile);
//	}
//}

