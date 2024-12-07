package org.kreyzon.postgres_monitor.dto.converter;

import lombok.experimental.UtilityClass;
import org.kreyzon.postgres_monitor.dto.DatabaseSizeDto;
import org.kreyzon.postgres_monitor.dto.PgStatActivityDto;
import org.kreyzon.postgres_monitor.model.PgStatActivity;

@UtilityClass
public class DTOConverter {

    public PgStatActivityDto getDtoFromEntity(PgStatActivity entity) {
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

    public DatabaseSizeDto getDtoFromEntity(Object[] row) {
        return DatabaseSizeDto
                .builder()
                .databaseName((String) row[0])
                .size((String) row[1])
                .build();
    }
}
