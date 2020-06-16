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
	//TODO: score update
}
