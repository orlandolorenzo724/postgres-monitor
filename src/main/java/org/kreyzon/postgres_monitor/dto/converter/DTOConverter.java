package org.kreyzon.postgres_monitor.dto.converter;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.kreyzon.postgres_monitor.dto.DatabaseSizeDto;
import org.kreyzon.postgres_monitor.dto.PgStatActivityDto;
import org.kreyzon.postgres_monitor.model.PgStatActivity;

@UtilityClass
@Slf4j
public class DTOConverter {

    public PgStatActivityDto getDto(PgStatActivity entity) {
        log.info("Converting entity to DTO: {}", entity);
        return PgStatActivityDto
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .pid(entity.getPid())
                .usesysid(entity.getUsesysid())
                .username(entity.getUsername())
                .applicationName(entity.getApplicationName())
                .clientAddress(entity.getClientAddress())
                .clientHostname(entity.getClientHostname())
                .clientPort(entity.getClientPort())
                .backendStart(entity.getBackendStart())
                .xactStart(entity.getXactStart())
                .queryStart(entity.getQueryStart())
                .stateChange(entity.getStateChange())
                .state(entity.getState())
                .query(entity.getQuery())
                .backendType(entity.getBackendType())
                .build();
    }

    public DatabaseSizeDto getDto(Object[] row) {
        log.info("Converting row to DTO: {}", row);
        return DatabaseSizeDto
                .builder()
                .databaseName(row[0].toString())
                .size(row[1].toString())
                .build();
    }
}