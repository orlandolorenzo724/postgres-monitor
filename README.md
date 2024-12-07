
## Postgres Monitor

**Postgres Monitor** is a Spring Boot application designed to monitor and analyze PostgreSQL database activity. It provides REST endpoints to retrieve insights about active connections, database sizes, and locks within a PostgreSQL instance.

---

### Features

1. **Monitor Active Connections**
    - Endpoint: `/pg_stat_activity`
    - Description: Returns a list of active connections for a given database.
    - Example Use Case: Diagnose which queries are currently consuming resources in your database.

2. **Monitor Database Sizes**
    - Endpoint: `/pg_database/sizes`
    - Description: Fetches the sizes of all databases in the PostgreSQL instance.
    - Example Use Case: Monitor storage utilization across multiple databases.

3. **Monitor Active Locks** *(Planned Feature)*
    - Endpoint: `/pg_locks/active`
    - Description: Displays active locks and blocking/blocked queries.
    - Example Use Case: Identify query bottlenecks due to locking.

---

### Technologies Used

1. **Spring Boot** (v3.2.5)
    - Spring Data JPA
    - Spring Web
2. **PostgreSQL**
    - Database driver for connectivity
    - System catalog usage for monitoring
3. **Testcontainers**
    - PostgreSQL container for integration testing
4. **Gson**
    - JSON parsing and pretty-printing for logs
5. **Lombok**
    - Simplifies code with annotations for boilerplate reduction

---

### Configuration

#### **Environment Variables**
- `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`: Database connection details.
- `PORT`: Port on which the server runs.

#### **Default Configuration (application.yml)**:
```yaml
server:
  port: ${PORT}
  servlet:
    context-path: /krz/postgres-monitor

spring:
  datasource:
    url: jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
    username: ${DB_USER}
    password: ${DB_PASSWORD}
  jpa:
    properties:
      hibernate:
        show_sql: true
        format_sql: true
```

---

### Installation and Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/kreyzon/postgres-monitor.git
   cd postgres-monitor
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   java -jar target/postgres-monitor-1.0-SNAPSHOT.jar
   ```

4. Access endpoints:
    - Active connections: `GET /pg_stat_activity?databaseName=<name>`
    - Database sizes: `GET /pg_database/sizes`

---

### Future Enhancements

- Add `/pg_locks/active` to monitor active locks and blocking queries.
- Implement detailed query analysis for expensive queries.
- Extend database monitoring to include performance metrics like cache hit ratio.

---

### License

This project is licensed under the MIT License. Feel free to use and modify it for your needs.
