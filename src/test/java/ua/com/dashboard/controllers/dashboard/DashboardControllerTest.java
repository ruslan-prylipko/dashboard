package ua.com.dashboard.controllers.dashboard;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class DashboardControllerTest {

    @Test
    @SneakyThrows
    void getDashboard() {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new DashboardController()).build();

        mockMvc.perform(get("/dashboard/"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"));
    }
}