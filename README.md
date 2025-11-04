# Flybase
A simple **Java command-line utility** that converts **JSON table definitions** into executable **SQL scripts**.  
Generates `CREATE`, `ALTER`, `INSERT`, `UPDATE`, `DELETE`, and `UPSERT` (Postgres) statements automatically.

----

## Features
- Generate SQL from JSON schema files  
- Supports:
  - `CREATE TABLE`, `ALTER TABLE`
  - `INSERT`, `UPDATE`, `DELETE`, `UPSERT`
- Handles `PRIMARY KEY`, `UNIQUE`, and `FOREIGN KEY`
- Outputs clean SQL scripts for PostgreSQL (extendable for MySQL, SQLite)
- Works as both CLI and Java library

---

## Build & Run
```bash
git clone https://github.com/abhinavsaurabh99/flybase.git
cd flybase
./gradlew build
java -jar build/libs/flybase.jar --input schema.json --output schema.sql
