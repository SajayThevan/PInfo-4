package api.msg;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.aerogear.kafka.SimpleKafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.service.ChallengeService;

@ExtendWith(MockitoExtension.class)
class ChallengeProducerTest {

	@Mock
	private SimpleKafkaProducer<String, String> kafkaProducer;
	@Mock
	private ChallengeService challengeService;

	@InjectMocks
	private ChallengeProducer producer;

	@Test
	void sendAcceptRecipe() {
		producer.sendAcceptRecipe("3");
		verify(kafkaProducer, times(1)).send("challengeAccepted", "3");
	}
}
