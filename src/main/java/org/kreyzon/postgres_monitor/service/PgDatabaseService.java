package org.kreyzon.postgres_monitor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kreyzon.postgres_monitor.dto.DatabaseSizeDto;
import org.kreyzon.postgres_monitor.dto.converter.DTOConverter;
import org.kreyzon.postgres_monitor.repository.PgDatabaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PgDatabaseService {

    private final PgDatabaseRepository pgDatabaseRepository;

    /**
     * Get the size of all databases
     *
     * @return list of database sizes
     */
    public List<DatabaseSizeDto> getAllDatabaseSizes() {
        log.info("Getting database sizes");
        return pgDatabaseRepository
                .getAllDatabaseSizes()
                .stream()
                .map(DTOConverter::getDto)
                .collect(Collectors.toList());
    }

    /**
     * Get the size of a specific database
     *
     * @param databaseName name of the database
     * @return database size
     */
    public DatabaseSizeDto getDatabaseSize(String databaseName) {
        log.info("Getting database size for database: {}", databaseName);
        Object[] row = pgDatabaseRepository.getDatabaseSize(databaseName);
        return DTOConverter.getDto((Object[]) row[0]);
    }
}