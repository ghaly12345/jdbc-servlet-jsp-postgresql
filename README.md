



# Student Database Management System (CRUD using Java Servlets & JDBC)

This project is a web application demonstrating **CRUD (Create, Read, Update, Delete)** operations for student records. It leverages **Java Servlets**, **JSP (JavaServer Pages)**, and **JDBC** for robust database interaction.

The application follows the classic **Model-View-Controller (MVC)** design pattern:
* **Model:** PostgreSQL Database / JDBC
* **View:** JSP (Frontend)
* **Controller:** Java Servlets (Backend Logic)

---

## 🛠️ Technology Stack

| Component | Technology | Details |
| :--- | :--- | :--- |
| **Backend Logic** | Java Servlets / JDBC | JDK 25 |
| **Frontend/View** | JSP (JavaServer Pages) | HTML, JavaScript |
| **Build Tool** | Apache Maven | Dependency Management |
| **Server** | Apache Tomcat 11.x | Configured via Smart Tomcat |
| **Database** | PostgreSQL | Hosted on **Aiven** (Remote) |
| **DB Client** | DBeaver | GUI for table management |

---

## ⚙️ Prerequisites

Ensure you have the following installed and configured on your local machine:

1.  **Java Development Kit (JDK 25)**
2.  **IntelliJ IDEA Community Edition** (with Smart Tomcat Plugin installed)
3.  **Apache Tomcat 11.x** (Installed locally)
4.  **Git**

---
## ☁️ Setting up Remote Database (Aiven)

Since this project uses a cloud database, follow these steps to set up your PostgreSQL instance:

1.  **Create Account:** Go to [Aiven.io](https://aiven.io/) and sign up for a free trial or free tier account.
2.  **Create Service:**
    * Click **Create Service**.
    * Select **PostgreSQL**.
    * Choose a Cloud Provider (e.g., Google Cloud, AWS) and Region.
    * Select the **Free Plan** (if available) or the smallest Hobbyist tier.
    * Name your service (e.g., `student-db`).
3.  **Get Connection Details:**
    * Once the service status is "Running", click on the service name.
    * Look for the **Connection Information** tab.
    * Click on Quick Connect and select Connect with dropdown as - Java, Copy the **Service URI** or note down the *Host, Port, User,* and *Password*.
    * *Note:* Aiven requires SSL. You will need to add `?sslmode=require` to your JDBC URL later. if you direct copy from connection detail tab without choosing java.

---
## 🚀 Setup & Configuration

### 1. Maven Dependencies (`pom.xml`)
Ensure your `pom.xml` includes the Jakarta EE Servlet API and the PostgreSQL JDBC Driver.

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>3.8.1</version>
    <scope>test</scope>
</dependency>


<dependency>
<groupId>jakarta.servlet</groupId>
<artifactId>jakarta.servlet-api</artifactId>
<version>6.1.0</version>
<scope>provided</scope>
</dependency>

<dependency>
<groupId>jakarta.servlet.jsp</groupId>
<artifactId>jakarta.servlet.jsp-api</artifactId>
<version>3.1.1</version>
<scope>provided</scope>
</dependency>

<dependency>
<groupId>jakarta.servlet.jsp.jstl</groupId>
<artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
<version>3.0.0</version>
</dependency>

<dependency>
<groupId>org.postgresql</groupId>
<artifactId>postgresql</artifactId>
<version>42.7.8</version>
</dependency>
````

### 2\. Database Schema

Run the following SQL query in your database client (DBeaver, pgAdmin, etc.) to create the required table.

```sql
CREATE TABLE studentdetails (
    stuid SERIAL PRIMARY KEY,
    stuname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phonenum VARCHAR(15) NOT NULL
);
```

### 3\. Database Connection (`DbUtil.java`)

Update the `DbUtil.java` class with your specific PostgreSQL connection credentials.

```java
// DbUtil.java
public class DbUtil {
    // NOTE: Use your actual Aiven or Localhost credentials here.
    public static String url = "jdbc:postgresql://[YOUR_HOST]:[YOUR_PORT]/defaultdb";
    public static String user = "avnadmin"; // Or your local user
    public static String password = "YOUR_PASSWORD";
    public static String driver = "org.postgresql.Driver"; 
}
```

-----

## 🏃‍♂️ How to Run

1.  **Build Project:**
    * Open the Maven panel in IntelliJ.
    * Run the goal `clean install`. This compiles the Servlets and packages the WAR file.
2.  **Run Tomcat:**
    * Start the application using the **Smart Tomcat** run configuration.
    * Ensure the context path is set to `/ServletsPostgre`.
3.  **Access Application:**
    * Open your web browser and navigate to:
    * `http://localhost:8081/ServletsPostgre/Home.jsp`

-----

## 📂 Application Flow & Architecture

The application routing is handled via specific JSP pages mapped to their corresponding Servlets.

| Page (`.jsp`) | Description | Servlet Controller | HTTP Method |
| :--- | :--- | :--- | :--- |
| **Home.jsp** | Main navigation dashboard. | *N/A (Direct Access)* | `GET` |
| **Insert.jsp** | Form to add a new student. | `InsertDetails.java` | `POST` |
| **Delete.jsp** | Form to delete via ID. | `DeleteDetails.java` | `POST` |
| **Select.jsp** | Form to find student by ID. | `SelectDetails.java` | `GET` |
| **Update.jsp** | Form to edit student info. | `UpdateDetails.java` | `POST` |
| **Result.jsp** | Displays fetched data. | *Redirected from Select* | `GET` |
| **Success.jsp** | Operation success message. | *Redirected from CRUD* | `GET` |

### Servlet Logic Summary

All Servlets utilize standard JDBC patterns (Load Driver $\rightarrow$ Get Connection $\rightarrow$ Prepare Statement $\rightarrow$ Execute $\rightarrow$ Close).

* **`InsertDetails.java`**: Executes `INSERT INTO studentdetails VALUES...`
* **`DeleteDetails.java`**: Executes `DELETE FROM studentdetails WHERE stuid=?`
* **`SelectDetails.java`**: Executes `SELECT * FROM studentdetails WHERE stuid=?`
    * *Note:* Retrieves the `ResultSet` and redirects to `Result.jsp` with query parameters.
* **`UpdateDetails.java`**: Executes `UPDATE studentdetails SET... WHERE stuid=?`

> **Note:** All Servlets use the `@WebServlet` annotation (e.g., `@WebServlet("/InsertDetails")`), eliminating the need for a complex `web.xml` configuration.





