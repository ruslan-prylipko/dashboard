package ua.com.dashboard.controllers.main;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


class MainControllerTest {

    @Test
    @SneakyThrows
    void getMainPage() {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new MainController()).build();

        mockMvc.perform(get("/dashboard/"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"));
    }
}