# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

A Maven multi-module educational project demonstrating two approaches to Spring + JPA (Hibernate) integration, both backed by an in-memory H2 database using an HR schema (Regions, Countries, Locations, Departments, Jobs, Employees).

## Modules

| Module | Spring | Config style | Connection pool | JUnit |
|---|---|---|---|---|
| `xml-config` | 4.3.2 | XML Spring beans + `persistence.xml` | BoneCP | JUnit 4 |
| `annotations-config` | 5.1.0 | Java `@Configuration` classes | HikariCP | JUnit 5 (Jupiter) |

Both modules share the same domain model and the same H2 in-memory database configured via `jpa-training.properties`.

## Build & Test Commands

```bash
# Build all modules
mvn clean install

# Build a single module
mvn clean install -pl annotations-config
mvn clean install -pl xml-config

# Run all tests in a module
mvn test -pl annotations-config

# Run a single test class
mvn test -pl annotations-config -Dtest=CountryServiceIT

# Run a single test method
mvn test -pl annotations-config -Dtest=CountryServiceIT#readAllCountries
```

## Architecture

### `xml-config` module
- Spring context assembled from three XML files in `src/main/resources/spring/`:
  - `application-config.xml` — root context, imports the others
  - `datasource-config.xml` — DataSource bean
  - `data-access-config.xml` — `EntityManagerFactory` (references `persistence.xml`), `TransactionManager`, `SharedEntityManagerBean`, enables `<tx:annotation-driven/>` and `<jpa:repositories>`
- `persistence.xml` (`META-INF/`) declares the `HR_PU` persistence unit and lists every entity class explicitly.
- DAOs extend `BaseDaoImpl` which holds a `@PersistenceContext EntityManager` and provides typed JPQL helpers. Extra utilities (`EntityManagerUtils`, `PersistenceManager`) exist for standalone (non-Spring) usage.

### `annotations-config` module
- Spring context built via two `@Configuration` classes in `edu.link.config`:
  - `AppConfig` — `@ComponentScan("edu.link.jpa")` + `@Import(DatabaseConfig.class)`
  - `DatabaseConfig` — declares `DataSource` (HikariCP), `EntityManagerFactory`, `JpaVendorAdapter`, and `JpaTransactionManager` beans; reads `jpa-training.properties`; enables `@EnableTransactionManagement` and `@EnableJpaRepositories("edu.link.jpa.dao")`
- Entity scanning replaces `persistence.xml`; Hibernate auto-creates the schema on startup (`hbm2ddl.auto=create`).
- Services (`@Service @Transactional`) wrap DAOs and are the transaction boundary.
- Integration tests use `@ExtendWith(SpringExtension.class)` + `@ContextConfiguration(classes = AppConfig.class)` and load test data with `@Sql("classpath:sql/data.sql")`.

### Shared domain model
`Region → Country → Location → Department ↔ Employee ↔ Job`  
`Employee` has a self-referencing manager relationship and a `@OneToMany` to `Phone`.  
`JobHistory` uses a composite PK (`JobHistoryPK`).  
`DepartmentDetails` is an `@Embeddable` embedded in `Department`.
