package api.msg;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProfileConsumerTest {

	@Mock
	private ProfileProducer producer;
	
	@InjectMocks
	private ProfileConsumer consumer;
	
	@Test
	void testUpdateRegularProfile() {
		consumer.updateProfile("452");
		verify(producer).send("452");
	}
	
	@Test
	void testUpdateAllProfile() {
		consumer.updateProfile("all");
		verify(producer, times(1)).sendAllProfiles();
	}
	


}
