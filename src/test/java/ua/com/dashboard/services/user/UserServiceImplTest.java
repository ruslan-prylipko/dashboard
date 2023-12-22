package ua.com.dashboard.services.user;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ua.com.dashboard.repositories.user.UserRepository;
import ua.com.dashboard.view.user.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserServiceImpl userService;
	
	@Test
	void testSave() {
		
		User userBeforeSave = new User("me", "me", "me", "100");
		User userAfterSave = new User("me", "me", "me", "100");
		userAfterSave.setId(1);
		
		doReturn(userAfterSave).when(userRepository).save(userBeforeSave);
		
		userService.save(userBeforeSave);
		
		verify(userRepository, times(1)).save(userBeforeSave);
	}
	
	@Test
	void testFindByUsername() {
		
		User[] usersBD = new User[] {
				new User(1, "me", "me", "me", "100", "ROLE_USER"),
				new User(2, "i", "i", "i", "100", "ROLE_USER"),
				new User(3, "you", "you", "you", "100", "ROLE_USER"),
		};
		
		doReturn(usersBD[1]).when(userRepository).findByUsername("i");
		
		userService.findByUsername("i");
		
		verify(userRepository, times(1)).findByUsername("i");
	}
	
	@Test
	void testLoadUserByUsernameIncorrectUsername() {
		
		when(userService.findByUsername("i")).thenReturn(null);
		
		assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("i"));
	}
	
	@Test
	void testLoadUserByUsernameCorrectUsername() {
		
		User user = new User(2, "i", "i", "i", "100", "ROLE_USER");
		
		org.springframework.security.core.userdetails.User userSpring = 
				new org.springframework.security.core.userdetails.User("i", "i", List.of(new SimpleGrantedAuthority("ROLE_USER")));
	
		when(userService.findByUsername("i")).thenReturn(user);
		
		assertEquals(userSpring, userService.loadUserByUsername("i"));
	}
}













