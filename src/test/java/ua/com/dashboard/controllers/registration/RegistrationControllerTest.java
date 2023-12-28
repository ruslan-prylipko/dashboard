package ua.com.dashboard.controllers.registration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.com.dashboard.services.user.UserService;
import ua.com.dashboard.view.user.User;
import ua.com.dashboard.view.user.role.Role;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private RegistrationController registrationController;

    @Test
    @SneakyThrows
    void getRegPage() {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.registrationController).build();

        mockMvc.perform(get("/regpage"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }


    @Test
    @SneakyThrows
    void registrationPassedCorrectData() {
    	
    	List<Role> roles = new ArrayList<>() {
			{
				add(new Role("ROLE_USER"));
			}
		};

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.registrationController).build();

        User user = new User("Alice", "Johnson", "alice.johnson", "P@ssw0rd1");
        user.setRoles(roles);
        User userForReturn = new User(1, "Alice", "Johnson", "alice.johnson", "P@ssw0rd1", roles);

        doReturn(userForReturn).when(this.userService).save(user);

        mockMvc.perform(post("/registration")
                        .param("firstName", "Alice")
                        .param("lastName", "Johnson")
                        .param("username", "alice.johnson")
                        .param("password", "P@ssw0rd1"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"));

        verify(userService, times(1)).save(user);
    }

    @Test
    @SneakyThrows
    void registrationPassedIncorrectData() {
        assertThrows(IllegalArgumentException.class,
                () -> this.registrationController
                        .registration("",   // incorrect parameter
                                "Johnson",
                                "alice.johnson",
                                "P@ssw0rd1"));

        assertThrows(NullPointerException.class,
                () -> this.registrationController
                        .registration("Alice",
                                null,   // incorrect parameter
                                "alice.johnson",
                                "P@ssw0rd1"));
    }
}