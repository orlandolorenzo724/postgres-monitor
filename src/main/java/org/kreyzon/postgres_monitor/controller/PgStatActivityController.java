package org.kreyzon.postgres_monitor.controller;

import lombok.RequiredArgsConstructor;
import org.kreyzon.postgres_monitor.dto.PgStatActivityDto;
import org.kreyzon.postgres_monitor.service.PgStatActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pg_stat_activity")
@RequiredArgsConstructor
public class PgStatActivityController {

    private final PgStatActivityService service;

    @GetMapping
    public ResponseEntity<List<PgStatActivityDto>> getActiveConnections(@RequestParam String databaseName) {
        return ResponseEntity.ok(service.getActiveConnections(databaseName));
    }
}
