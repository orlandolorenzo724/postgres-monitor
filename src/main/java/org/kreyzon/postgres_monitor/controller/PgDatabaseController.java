package org.kreyzon.postgres_monitor.controller;

import lombok.RequiredArgsConstructor;
import org.kreyzon.postgres_monitor.dto.DatabaseSizeDto;
import org.kreyzon.postgres_monitor.service.PgDatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pg_database")
@RequiredArgsConstructor
public class PgDatabaseController {

    private final PgDatabaseService pgDatabaseService;

    @GetMapping("/sizes")
    public ResponseEntity<List<DatabaseSizeDto>> getDatabaseSizes() {
        return ResponseEntity.ok(pgDatabaseService.getDatabaseSizes());
    }
}
