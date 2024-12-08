package org.kreyzon.postgres_monitor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kreyzon.postgres_monitor.repository.PgStatDatabaseRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PgStatDatabaseService {

    private final PgStatDatabaseRepository pgStatDatabaseRepository;

    public Double getCacheHitRatio() {
        log.info("Getting cache hit ratio");
        return pgStatDatabaseRepository.getCacheHitRatioPercentage();
    }
}