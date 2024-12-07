package org.kreyzon.postgres_monitor.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DatabaseSizeDto {
    String databaseName;
    String size;
}

