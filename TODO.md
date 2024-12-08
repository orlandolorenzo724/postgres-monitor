# TODO

## Features

- [x] **Monitor Active Connections by Database Name**
  - Implemented to fetch all active connections filtered by database.

- [x] **Monitor Database Sizes**
  - Retrieves the sizes of all databases in the PostgreSQL instance.

- [X] **Monitor Active Queries**
  - Planned: Implement functionality to track currently running queries with execution details.

- [ ] **Monitor Active Locks**
  - Planned: Identify active locks and provide information on blocking and blocked queries.

- [ ] **Track Query Execution Times**
  - Planned: Use `pg_stat_statements` to identify the most time-consuming queries.

- [ ] **Database Health Metrics**
  - Planned: Monitor overall database health, including cache hit ratio.

- [ ] **Monitor Table Statistics**
  - Planned: Display table-level statistics like row count, size, and vacuum status.

- [ ] **Alerting and Notifications**
  - Planned: Add functionality to send alerts for high resource usage, locks, or long-running queries.

- [ ] **Historical Data Analysis**
  - Planned: Store and analyze historical data for trends in query performance and database usage.

- [ ] **Generate Detailed Reports**
  - Planned: Provide downloadable reports for active connections, query performance, and database sizes.

