package com.example.taskapi;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType; import org.springframework.test.web.servlet.MockMvc;
@SpringBootTest @AutoConfigureMockMvc
class TaskControllerTest {
    @Autowired private MockMvc mockMvc;
    @Test void list() throws Exception { mockMvc.perform(get("/api/todos")).andExpect(status().isOk()).andExpect(jsonPath("$").isArray()); }
    @Test void create() throws Exception { mockMvc.perform(post("/api/todos").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"test\"}")).andExpect(status().isCreated()).andExpect(header().exists("Location")).andExpect(jsonPath("$.id").isNumber()); }
    @Test void missing() throws Exception { mockMvc.perform(get("/api/todos/9999")).andExpect(status().isNotFound()); }
}
