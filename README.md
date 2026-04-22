# Visitor Entry Management System (GUI Version)

A professional, feature-rich Java Swing application for managing visitor logs. This project has been converted from a basic CLI to a full Graphical User Interface (GUI) with complete CRUD (Create, Read, Update, Delete) functionality and MySQL database integration.

## 🚀 Features

- **Modern Dashboard**: Simple and intuitive navigation with a two-button entry system.
- **Visitor Entry**: Dedicated form window for adding new visitors with validation.
- **Live Visitor List**: A tabular view of all visitor records fetched in real-time from the database.
- **Update Records**: Edit visitor details directly from the list view.
- **Delete Records**: Remove visitor logs with a confirmation prompt.
- **Clean Architecture**: Follows the DAO (Data Access Object) and Service layer patterns for better maintainability.

## 🛠️ Technology Stack

- **Language**: Java 8+
- **GUI Framework**: Java Swing
- **Database**: MySQL
- **Driver**: MySQL Connector/J

## 📋 Prerequisites

Before running the application, ensure you have:
1. **JDK** installed (Java Development Kit).
2. **MySQL Server** installed and running.
3. A database named `visitordb` with a `visitors` table.

### Database Setup
```sql
CREATE DATABASE visitordb;

USE visitordb;

CREATE TABLE visitors (
    visitor_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    purpose VARCHAR(255) NOT NULL
);
```

## ⚙️ Configuration

Update the database credentials in `db/DBConnection.java`:
```java
con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/visitordb",
    "root",         // Your MySQL username
    "your_password" // Your MySQL password
);
```

## 🏃 How to Run

### 1. Compile
Open your terminal in the project root and run:
```powershell
javac -encoding UTF-8 -cp ".;lib/mysql-connector-j.jar" dao/*.java daoimpl/*.java db/*.java dto/*.java main/*.java service/*.java
```

### 2. Run
```powershell
java -cp ".;lib/mysql-connector-j.jar" main.MainApp
```

## 📂 Project Structure

```text
VisitorEntryManagementSystem/
├── dao/           # Data Access Object Interfaces
├── daoimpl/       # Database implementation logic
├── db/            # Connection configuration
├── dto/           # Data Transfer Objects
├── lib/           # JAR dependencies (MySQL Driver)
├── main/          # Entry point and GUI Frames
└── service/       # Business logic layer
```

