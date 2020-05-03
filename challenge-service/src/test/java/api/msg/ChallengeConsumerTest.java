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
		System.out.println("-----------------DEBUT TEST CONSUMER UpdateRegularChallenge-----------------");
		consumer.updateChallenge("452");
		verify(producer).send(452l);
		System.out.println("-----------------FIN TEST CONSUMER UpdateRegularChallenge-----------------");
	}
	
	@Test
	void testUpdateAllChallenge() {
		System.out.println("-----------------DEBUT TEST CONSUMER testUpdateAllChallenge-----------------");
		consumer.updateChallenge("all");
		verify(producer, times(1)).sendAllChallenges();
		System.out.println("-----------------FIN TEST CONSUMER testUpdateAllChallenge-----------------");
	}
	
	@Test
	void testUpdateUnexpectedMessage() {
		System.out.println("-----------------DEBUT TEST CONSUMER testUpdateUnexpectedMessage-----------------");
		assertThrows(IllegalArgumentException.class,
				() -> consumer.updateChallenge("XXX"));
		System.out.println("-----------------FIN TEST CONSUMER testUpdateUnexpectedMessage-----------------");
	}

}
