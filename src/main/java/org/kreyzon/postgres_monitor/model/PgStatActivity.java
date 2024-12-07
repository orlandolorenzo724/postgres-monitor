package org.kreyzon.postgres_monitor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pg_stat_activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PgStatActivity {
    @Id
    @Column(name = "datid")
    private String id;

    @Column(name = "datname")
    private String name;

    @Column(name = "pid")
    private String pid;

    @Column(name = "usesysid")
    private String usesysid;

    @Column(name = "usename")
    private String username;

    @Column(name = "application_name")
    private String applicationName;

    @Column(name = "client_addr")
    private String clientAddress;

    @Column(name = "client_hostname")
    private String clientHostname;

    @Column(name = "client_port")
    private String clientPort;

    @Column(name = "backend_start")
    private LocalDateTime backendStart;

    @Column(name = "xact_start")
    private LocalDateTime xactStart;

    @Column(name = "query_start")
    private LocalDateTime queryStart;

    @Column(name = "state_change")
    private LocalDateTime stateChange;

    @Column(name = "state")
    private String state;

    @Column(name = "query")
    private String query;

    @Column(name = "backend_type")
    private String backendType;
}
