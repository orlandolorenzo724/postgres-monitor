package org.kreyzon.postgres_monitor.repository;

import org.kreyzon.postgres_monitor.model.PgStatActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface PgStatActivityRepository extends JpaRepository<PgStatActivity, String> {

    @Query(value = "SELECT * FROM pg_stat_activity " +
            "WHERE datid IS NOT NULL " +
            "AND state = 'active' " +
            "ORDER BY query_start DESC", nativeQuery = true)
    List<PgStatActivity> getAllActiveConnections();

    @Query(value = "SELECT * FROM pg_stat_activity " +
            "WHERE datid IS NOT NULL " +
            "AND datname = :database " +
            "ORDER BY backend_start DESC", nativeQuery = true)
    List<PgStatActivity> getActiveConnectionsByDatabase(@Param("database") String database);

    @Query(value = "SELECT * FROM pg_stat_activity " +
            "WHERE datid IS NOT NULL " +
            "AND datname = :database " +
            "AND state = 'active' " +
            "ORDER BY query_start DESC", nativeQuery = true)
    List<PgStatActivity> getActiveQueriesByDatabase(@Param("database") String database);

    @Query(value = "SELECT * FROM pg_stat_activity " +
            "WHERE datid IS NOT NULL " +
            "AND state = 'active' " +
            "ORDER BY query_start DESC", nativeQuery = true)
    List<PgStatActivity> getAllActiveQueries();
}