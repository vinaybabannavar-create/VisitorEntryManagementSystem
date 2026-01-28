# Visitor Entry Management System

A console-based Java application to manage visitor entries using MySQL and JDBC.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL Server
- MySQL JDBC Driver (Connector/J)

## Setup

1.  **Database Setup**:
    - Open MySQL Workbench or your preferred SQL tool.
    - Run the script `schema.sql` to create the database and table.

2.  **Configuration**:
    - Open `src/com/visitor/util/DBConnection.java`.
    - Update the `USERNAME` and `PASSWORD` constants with your MySQL credentials.

3.  **Compilation**:
    ```bash
    javac -d bin -cp "lib/mysql-connector-j-8.x.x.jar;src" src/com/visitor/main/MainApp.java src/com/visitor/dto/*.java src/com/visitor/dao/*.java src/com/visitor/service/*.java src/com/visitor/util/*.java
    ```
    *(Note: Ensure you have the MySQL connector JAR in a `lib` folder or adjust the classpath accordingly)*

4.  **Execution**:
    ```bash
    java -cp "bin;lib/mysql-connector-j-8.x.x.jar" com.visitor.main.MainApp
    ```

## Architecture

- **DTO**: Data Transfer Objects (POJOs)
- **DAO**: Data Access Object (Database operations)
- **Service**: Business Logic
- **Main**: User Interface (Console)
