package com.game2048;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testMoveEndpoint() throws Exception {
        mockMvc.perform(post("/api/move?direction=UP"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.score").exists());
    }

    @Test
    void testResetEndpoint() throws Exception {
        mockMvc.perform(post("/api/reset"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.grid").isArray());
    }
}