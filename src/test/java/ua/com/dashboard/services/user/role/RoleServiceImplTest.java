package ua.com.dashboard.services.user.role;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.dashboard.repositories.user.role.RoleRepository;
import ua.com.dashboard.view.user.role.Role;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {
	
	@Mock
	private RoleRepository roleRepository;
	@InjectMocks
	private RoleServiceImpl roleService;
	
	@Test
	void testSave() {
		
		Role role = new Role("ROLE_USER");
		Role savedRole = new Role("ROLE_USER");
		savedRole.setId(1);
		
		doReturn(savedRole).when(roleRepository).save(role);
		
		roleService.save(role);
		
		verify(roleRepository, times(1)).save(role);
	}
}
