package org.kreyzon.postgres_monitor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kreyzon.postgres_monitor.dto.PgStatActivityDto;
import org.kreyzon.postgres_monitor.dto.converter.DTOConverter;
import org.kreyzon.postgres_monitor.repository.PgStatActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PgStatActivityService {

    private final PgStatActivityRepository pgStatActivityRepository;

    /**
     * Get active connections for a given database
     *
     * @param database database name
     * @return list of active connections
     */
    public List<PgStatActivityDto> getActiveConnections(String database) {
        log.info("Getting active connections for database: {}", database);
        return pgStatActivityRepository.findConnectionsByDatabase(database)
                .stream()
                .map(DTOConverter::getDtoFromEntity)
                .toList();
    }
}