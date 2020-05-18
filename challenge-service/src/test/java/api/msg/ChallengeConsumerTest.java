package api.msg;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChallengeConsumerTest {

	@Mock
	private ChallengeProducer producer;
	
	@InjectMocks
	private ChallengeConsumer consumer;
	
	@Test
	void testUpdateRegularChallenge() {
		consumer.updateChallenge("452");
		verify(producer).send(452l);
	}
	
	@Test
	void testUpdateAllChallenge() {
		consumer.updateChallenge("all");
		verify(producer, times(1)).sendAllChallenges();
	}
	
	@Test
	void testUpdateUnexpectedMessage() {
		assertThrows(IllegalArgumentException.class,
				() -> consumer.updateChallenge("XXX"));
	}

}
