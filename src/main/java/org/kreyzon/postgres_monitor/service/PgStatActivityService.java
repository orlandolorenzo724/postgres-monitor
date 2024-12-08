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
     * Get all active connections
     *
     * @return list of active connections
     */
    public List<PgStatActivityDto> getAllActiveConnections() {
        log.info("Getting all active connections");
        return pgStatActivityRepository.getAllActiveConnections()
                .stream()
                .map(DTOConverter::getDto)
                .toList();
    }

    /**
     * Get active connections for a given database
     *
     * @param database database name
     * @return list of active connections
     */
    public List<PgStatActivityDto> getActiveConnectionsByDatabase(String database) {
        log.info("Getting active connections for database: {}", database);
        return pgStatActivityRepository.getActiveConnectionsByDatabase(database)
                .stream()
                .map(DTOConverter::getDto)
                .toList();
    }

    public List<PgStatActivityDto> getAllActiveQueries() {
        log.info("Getting all active queries");
        return pgStatActivityRepository.getAllActiveQueries()
                .stream()
                .map(DTOConverter::getDto)
                .toList();
    }

    /**
     * Get the active queries for a database
     *
     * @param database the database name
     * @return list of active queries
     */
    public List<PgStatActivityDto> getActiveQueriesByDatabase(String database) {
        log.info("Getting active queries for database: {}", database);
        return pgStatActivityRepository.getActiveQueriesByDatabase(database)
                .stream()
                .map(DTOConverter::getDto)
                .toList();
    }
}