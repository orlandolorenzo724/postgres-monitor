package org.kreyzon.postgres_monitor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kreyzon.postgres_monitor.dto.PgStatDatabaseDto;
import org.kreyzon.postgres_monitor.dto.converter.DTOConverter;
import org.kreyzon.postgres_monitor.repository.PgStatDatabaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PgStatDatabaseService {

    private final PgStatDatabaseRepository pgStatDatabaseRepository;

    public Double getCacheHitRatio() {
        log.info("Getting cache hit ratio");
        return pgStatDatabaseRepository.getCacheHitRatioPercentage();
    }

    public List<PgStatDatabaseDto> getDatabaseStats() {
        log.info("Getting database stats");
        List<Object[]> results = pgStatDatabaseRepository.getDatabaseStats();

        return results.stream()
                .map(record -> new PgStatDatabaseDto(
                        null,
                        (String) record[0],       // datname
                        (Integer) record[1],      // numbackends
                        (Long) record[2],         // xact_commit
                        (Long) record[3]          // xact_rollback
                ))
                .toList();
    }
}