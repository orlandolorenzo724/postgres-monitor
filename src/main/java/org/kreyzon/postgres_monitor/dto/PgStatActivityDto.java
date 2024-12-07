package org.kreyzon.postgres_monitor.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.kreyzon.postgres_monitor.model.PgStatActivity}
 */
@Value
@Builder
public class PgStatActivityDto implements Serializable {
    String id;
    String name;
    String pid;
    String usesysid;
    String username;
    String applicationName;
    String clientAddress;
    String clientHostname;
    String clientPort;
    LocalDateTime backendStart;
    LocalDateTime xactStart;
    LocalDateTime queryStart;
    LocalDateTime stateChange;
    String state;
    String query;
    String backendType;
}