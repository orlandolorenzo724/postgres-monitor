package org.kreyzon.postgres_monitor.repository;

import org.kreyzon.postgres_monitor.dto.PgStatDatabaseDto;
import org.kreyzon.postgres_monitor.model.PgStatDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgStatDatabaseRepository extends JpaRepository<PgStatDatabase, String> {


    @Query(value = "SELECT ROUND((SUM(blks_hit) * 100.0 / (SUM(blks_hit) + SUM(blks_read))), 2) AS cache_hit_ratio_percentage " +
            "FROM pg_stat_database", nativeQuery = true)
    Double getCacheHitRatioPercentage();


    @Query(value = "SELECT datname, numbackends, xact_commit, xact_rollback FROM pg_stat_database", nativeQuery = true)
    List<Object[]> getDatabaseStats();
}
