package api.msg;


//import org.junit.jupiter.api.Test;
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
	
//	@Test  hard to test because the producer is in another service
//	void testDeleteChallenge() {
//
//	}
	


}
