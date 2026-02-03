# 📚 API Automation Framework – Online Bookstore

This repository contains a **professional-grade API automation framework** designed for the **Online Bookstore** system.  
Developed by **Ata Pourfarivarnezhad**, this project demonstrates **advanced API test automation practices** using **Java 21**, **Rest-Assured**, and **TestNG**.

The framework is built with **scalability, maintainability, and real-world project standards** in mind.

---

## 🏗 Project Architecture & Design Patterns

The framework follows a **decoupled, modular architecture** to ensure long-term maintainability and easy extension.

### 📁 Package Structure

- **clients**  
  Contains `BookClient` and `AuthorClient`.  
  These classes act as an **abstraction layer** over API endpoints and include **Allure `@Step` annotations** to produce clean, human-readable execution logs.

- **models**  
  Implements POJOs for `Book` and `Author` entities.  
  - **Lombok** is used to eliminate boilerplate code  
  - **Jackson** handles high-performance JSON serialization/deserialization

- **specs**  
  Contains the `SpecFactory`, which centralizes the `RequestSpecification`.  
  - Global configuration for headers, base URI, and logging  
  - Allure filters automatically capture **every request and response body**

- **tests**  
  Organized into logical test suites for **Books** and **Authors**, covering:
  - CRUD operations  
  - Boundary value analysis  
  - Negative and resilience scenarios

---

## 🧪 Test Coverage & Scenarios

The framework contains **13 high-impact automated test cases** with a **100% pass rate**.

### 📘 Books API (7 Test Cases)

**Happy Path Scenarios**
- Create a book
- Retrieve a book by ID
- Retrieve all books
- Update a book with full payload
- Delete a book

**Edge & Negative Scenarios**
- Verify `404 Not Found` for non-existing book IDs
- Confirm successful deletion behavior

---

### ✍️ Authors API (6 Test Cases – Bonus Task)

**Business Logic Validation**
- Full CRUD coverage for authors

**Resilience Testing**
- Validate system behavior when querying or deleting non-existent authors

---

## 🐳 Docker Execution (Optimized for macOS)

The framework is **fully containerized** to guarantee consistent execution across environments.

To avoid:
- Permission conflicts
- Stale Allure data
- Report pollution on macOS

Use the following **Clean-and-Run strategy**.

### 1️⃣ Build the Docker Image
```bash
docker build -t bookstore-automation-ata .
```

### 2️⃣ Run with Automated Cleanup
This command removes old results **before execution**, ensuring the Allure report contains **only the latest 13 test results**:
```bash
sudo rm -rf allure-results && docker run --rm -v "$(pwd)/allure-results:/app/target/allure-results" bookstore-automation-ata
```

---

## 📊 Advanced Reporting – Allure

The framework generates **rich, interactive Allure reports**.

Features include:
- Full request & response logging
- Step-by-step execution visibility
- Execution timelines
- Clear pass/fail analytics

### View the Report
```bash
allure serve allure-results
```

The dashboard will display:
- ✅ 100% success rate  
- 📈 Detailed execution timelines  
- 🔍 Full API request/response metadata

---

## 🛠 Tech Stack

- **Language:** Java 21 (Latest LTS)
- **API Library:** Rest-Assured 5.5.6
- **Test Engine:** TestNG 7.11.0
- **Reporting:** Allure 2.32.0
- **Build Tool:** Maven
- **Infrastructure:** Docker

---

## 🎯 Key Highlights

- Real-world API automation architecture  
- Clean separation of concerns  
- Dockerized execution  
- Enterprise-level reporting  
- Interview-ready project structure  

This project is designed to reflect **how API automation is implemented in real production teams**, not just demo examples.
