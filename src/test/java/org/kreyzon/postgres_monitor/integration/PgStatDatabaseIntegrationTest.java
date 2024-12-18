package org.kreyzon.postgres_monitor.integration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PgStatDatabaseIntegrationTest {

    private static final String DATABASE_NAME = "test_db";

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static PostgreSQLContainer<?> postgresContainer;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void setup() {
        postgresContainer = new PostgreSQLContainer<>("postgres:15.3")
                .withDatabaseName(DATABASE_NAME)
                .withUsername("testuser")
                .withPassword("testpass");
        postgresContainer.start();

        System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgresContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresContainer.getPassword());

        log.info("PostgreSQL container started with JDBC URL: {}", postgresContainer.getJdbcUrl());
    }

    @Test
    @Order(1)
    void shouldReturnCacheHitRatio() throws Exception {
        log.info("Requesting cache hit ratio");
        String response = mockMvc.perform(get("/pg_stat_database/cache_hit_ratio")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        log.info("Response: {}{}", gson.toJson(gson.fromJson(response, Object.class)), "%");
        Assertions.assertTrue(Double.parseDouble(response) >= 0);
    }

    @Test
    @Order(2)
    void shouldReturnDatabaseStats() throws Exception {
        log.info("Requesting database stats");
        String response = mockMvc.perform(get("/pg_stat_database/database_stats")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        log.info("Response: {}", gson.toJson(gson.fromJson(response, Object.class)));
        Assertions.assertTrue(response.contains(DATABASE_NAME));
    }
}
