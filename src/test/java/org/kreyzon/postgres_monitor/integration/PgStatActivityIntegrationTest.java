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
public class PgStatActivityIntegrationTest {

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
    void shouldReturnAllActiveConnections() throws Exception {
        log.info("Requesting all active connections");
        String response = mockMvc.perform(get("/pg_stat_activity/active_connections")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        log.info("Response: {}", gson.toJson(gson.fromJson(response, Object.class)));
        Assertions.assertTrue(response.contains("datid"));
    }

    @Test
    @Order(2)
    void shouldReturnActiveConnectionsForDatabase() throws Exception {
        log.info("Requesting active connections for database: {}", DATABASE_NAME);
        String response = mockMvc.perform(get("/pg_stat_activity/active_connections/{databaseName}", DATABASE_NAME)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info("Response: {}", gson.toJson(gson.fromJson(response, Object.class)));
        Assertions.assertTrue(response.contains(DATABASE_NAME));
    }

    @Test
    @Order(3)
    void shouldReturnAllActiveQueries() throws Exception {
        log.info("Requesting all active queries");
        String response = mockMvc.perform(get("/pg_stat_activity/active_queries")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        log.info("Response: {}", gson.toJson(gson.fromJson(response, Object.class)));
        Assertions.assertTrue(response.contains("datid"));
    }

    @Test
    @Order(4)
    void shouldReturnActiveQueriesForDatabase() throws Exception {
        log.info("Requesting active queries for database: {}", DATABASE_NAME);
        String response = mockMvc.perform(get("/pg_stat_activity/active_queries/{databaseName}", DATABASE_NAME)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        log.info("Response: {}", gson.toJson(gson.fromJson(response, Object.class)));
        Assertions.assertTrue(response.contains(DATABASE_NAME));
    }
}