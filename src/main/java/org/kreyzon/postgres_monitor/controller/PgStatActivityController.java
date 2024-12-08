package org.kreyzon.postgres_monitor.controller;

import lombok.RequiredArgsConstructor;
import org.kreyzon.postgres_monitor.dto.PgStatActivityDto;
import org.kreyzon.postgres_monitor.service.PgStatActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pg_stat_activity")
@RequiredArgsConstructor
public class PgStatActivityController {

    private final PgStatActivityService service;

    @GetMapping("/active_connections")
    public ResponseEntity<List<PgStatActivityDto>> getAllActiveConnections() {
        return ResponseEntity.ok(service.getAllActiveConnections());
    }

    @GetMapping("/active_connections/{databaseName}")
    public ResponseEntity<List<PgStatActivityDto>> getActiveConnections(@PathVariable("databaseName") String databaseName) {
        return ResponseEntity.ok(service.getActiveConnectionsByDatabase(databaseName));
    }

    @GetMapping("/active_queries")
    public ResponseEntity<List<PgStatActivityDto>> getAllActiveQueries() {
        return ResponseEntity.ok(service.getAllActiveQueries());
    }

    @GetMapping("/active_queries/{databaseName}")
    public ResponseEntity<List<PgStatActivityDto>> getActiveQueries(@PathVariable("databaseName") String databaseName) {
        return ResponseEntity.ok(service.getActiveQueriesByDatabase(databaseName));
    }
}