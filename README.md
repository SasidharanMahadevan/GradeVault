# GradeVault

A console-based student records and grade management system built in Java, with full CRUD functionality, ranking, and persistent storage backed by a MySQL database.

This project started as a simple array-based console application and was progressively rebuilt to reflect real-world software design practices — including OOP principles, safer data access patterns, and a full migration from file-based storage to a relational database.

## Features

- **Add, search, update, delete, and rank students** by marks
- **Object-oriented design** — an abstract `Person` class extended by `Student`, with `Comparable` used for rank-based sorting
- **Full CRUD operations** backed by MySQL via JDBC
- **SQL-injection-safe queries** — all database operations use `PreparedStatement`
- **Robust error handling** — a dedicated exception handler distinguishes between authentication failures, unreachable database errors, and other SQL exceptions (verified through targeted failure testing)
- **Input validation** — duplicate ID checks, marks range validation (0–100), and crash-proofing against invalid menu input

## Tech Stack

- **Language:** Java
- **Database:** MySQL
- **Data Access:** JDBC (MySQL Connector/J)
- **Project Structure:** Plain Java project (no build tool)

## Architecture

The project evolved through a few key stages:

1. **V1 — In-memory, array-based:** `Student`, `ResultManager`, and `Main` classes managing records in a fixed-size array with linear search and a hand-written sort.
2. **V2 — OOP refinement:** Introduced an abstract `Person` class, `Student extends Person`, and replaced the hand-rolled sort with `Comparable`-based sorting.
3. **V3 — File persistence:** Added a `FileHandling` class to save/load records to disk in CSV format.
4. **V4 — Database-backed (current):** Replaced file-based persistence entirely with MySQL via a `DBHandling` class. `ResultManager` was refactored into a thin service layer that delegates all data operations to `DBHandling` through dependency injection, keeping the codebase clean and focused on a single source of truth for data.

## What I Learned

- Structuring a Java application around OOP principles rather than procedural logic
- Writing safe, parameterized SQL queries with `PreparedStatement`
- Designing clean layering between business logic (`ResultManager`) and data access (`DBHandling`)
- Handling real-world failure modes (wrong credentials, unreachable database) with meaningful error messages instead of raw stack traces
- Making deliberate architectural trade-offs — for example, evaluating connection pooling and choosing not to implement it, since it would be disproportionate for this project's scale

## Getting Started

1. Clone the repository
2. Set up a MySQL database and update the connection details in `DBHandling`
3. Run `Main.java` from your IDE or via the command line

## Future Improvements

- Add connection pooling for more efficient database access at scale
- Migrate to a build tool (Maven/Gradle) for dependency management
- Add a simple GUI or REST API layer on top of the existing business logic
