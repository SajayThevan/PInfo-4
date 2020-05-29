package api.msg;

import javax.enterprise.context.ApplicationScoped;
import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;
import lombok.extern.java.Log;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{thorntail.kafka-configuration.host}:#{thorntail.kafka-configuration.port}")
@Log
public class ProfileProducer {
	
	@Producer
	private SimpleKafkaProducer<String, String> producer;
 			
	public void sendProfileDeleted(String profileId) {
		log.info("Send the state of an profile to the topic with id " + profileId);								  
			producer.send("profilDelete",profileId);
	}
}




