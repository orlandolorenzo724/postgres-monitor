package org.kreyzon.postgres_monitor.repository;

import org.kreyzon.postgres_monitor.model.PgDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgDatabaseRepository extends JpaRepository<PgDatabase, String> {

    @Query(value = "SELECT d.datname AS databaseName, " +
            "pg_size_pretty(pg_database_size(d.datname)) AS size " +
            "FROM pg_catalog.pg_database d " +
            "ORDER BY pg_database_size(d.datname) DESC", nativeQuery = true)
    List<Object[]> getAllDatabaseSizes();

    @Query(value = "SELECT d.datname AS databaseName, " +
            "pg_size_pretty(pg_database_size(d.datname)) AS size " +
            "FROM pg_catalog.pg_database d " +
            "WHERE d.datname = ?1", nativeQuery = true)
    Object[] getDatabaseSize(String databaseName);
}
