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
class ProfileConsumerTest {

	@Mock
	private ProfileProducer producer;
	
	@InjectMocks
	private ProfileConsumer consumer;
	
	@Test
	void testUpdateRegularProfile() {
		System.out.println("-----------------DEBUT TEST CONSUMER UpdateRegularProfile-----------------");
		consumer.updateProfile("452");
		verify(producer).send(452l);
		System.out.println("-----------------FIN TEST CONSUMER UpdateRegularProfile-----------------");
	}
	
	@Test
	void testUpdateAllProfile() {
		System.out.println("-----------------DEBUT TEST CONSUMER testUpdateAllProfile-----------------");
		consumer.updateProfile("all");
		verify(producer, times(1)).sendAllProfiles();
		System.out.println("-----------------FIN TEST CONSUMER testUpdateAllProfile-----------------");
	}
	
	@Test
	void testUpdateUnexpectedMessage() {
		System.out.println("-----------------DEBUT TEST CONSUMER testUpdateUnexpectedMessage-----------------");
		assertThrows(IllegalArgumentException.class,
				() -> consumer.updateProfile("XXX"));
		System.out.println("-----------------FIN TEST CONSUMER testUpdateUnexpectedMessage-----------------");
	}

}
