package org.kreyzon.postgres_monitor.repository;

import org.kreyzon.postgres_monitor.model.PgDatabase;
import org.kreyzon.postgres_monitor.dto.DatabaseSizeDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface PgDatabaseRepository extends Repository<PgDatabase, String> {

    @Query(value = "SELECT d.datname AS databaseName, " +
            "pg_size_pretty(pg_database_size(d.datname)) AS size " +
            "FROM pg_catalog.pg_database d " +
            "ORDER BY pg_database_size(d.datname) DESC", nativeQuery = true)
    List<Object[]> findDatabaseSizes();
}
