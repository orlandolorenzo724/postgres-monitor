package org.kreyzon.postgres_monitor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "pg_stat_database", schema = "pg_catalog")
public class PgStatDatabase {
    @Id
    @Column(name = "datid")
    private String id;


    @Column(name = "datname")
    private String name;

    @Column(name = "numbackends")
    private Integer activeConnections;

    @Column(name = "xact_commit")
    private Long transactionsCommitted;

    @Column(name = "xact_rollback")
    private Long transactionsRollback;
}