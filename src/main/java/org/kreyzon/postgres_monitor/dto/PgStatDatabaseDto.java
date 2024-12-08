package org.kreyzon.postgres_monitor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.kreyzon.postgres_monitor.model.PgStatDatabase}
 */
@Value
@AllArgsConstructor
@Builder
public class PgStatDatabaseDto implements Serializable {
    String id;
    String name;
    Integer activeConnections;
    Long transactionsCommitted;
    Long transactionsRollback;
}