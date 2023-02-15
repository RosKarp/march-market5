package ru.geekbrains.march.market.auth.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void securityAccessDeniedTest() throws Exception {
        mockMvc.perform(post("/authenticate"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void securityTokenTestWrongPass() throws Exception {
    String jsonRequest = "{\n" +
            "\t\"username\": \"bob\",\n" +
            "\t\"password\": \"101\"\n" +
            "}";

    mockMvc.perform(
                    post("/authenticate")
                            .content(jsonRequest)
                            .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isUnauthorized());
}
    @Test
    public void securityTokenTestRightPass() throws Exception {
        String jsonRequest = "{\n" +
                "\t\"username\": \"bob\",\n" +
                "\t\"password\": \"100\"\n" +
                "}";
        mockMvc.perform(
                        post("/authenticate")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
}
