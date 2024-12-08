package org.kreyzon.postgres_monitor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pg_database", schema = "pg_catalog")
public class PgDatabase {
    @Id
    @Column(name = "datname")
    private String name;
}