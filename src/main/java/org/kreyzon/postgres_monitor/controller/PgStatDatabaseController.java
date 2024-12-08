package org.kreyzon.postgres_monitor.controller;

import lombok.RequiredArgsConstructor;
import org.kreyzon.postgres_monitor.service.PgStatDatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pg_stat_database")
@RequiredArgsConstructor
public class PgStatDatabaseController {

    private final PgStatDatabaseService service;

    @GetMapping("/cache_hit_ratio")
    public ResponseEntity<Double> getCacheHitRatio() {
        return ResponseEntity.ok(service.getCacheHitRatio());
    }
}
