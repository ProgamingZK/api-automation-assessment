# 📚 API Automation Framework – Online Bookstore

This repository contains a **professional-grade API automation framework** designed for the **Online Bookstore** system.

Developed by **Ata Pourfarivarnezhad**, this project demonstrates **advanced API test automation practices** using **Java 21**, **Rest-Assured**, and **TestNG**.

The framework is built with **scalability, maintainability, and real-world project standards** in mind.

---

## 🚀 CI/CD & Quick Access (Reporting & Execution)

This project is fully integrated with **GitHub Actions** and **GitHub Pages** for seamless delivery and monitoring.

### 📊 1. View Live Interactive Report

You can access the **real-time execution dashboard** without running any code locally.  
The report includes:
- Full Request/Response logs  
- Step-by-step execution visibility  
- Detailed execution timelines  

👉 **View Live Allure Report https://atafarivar.github.io/api-automation-assessment/**

---

### ⚙️ 2. Manual Test Execution (GitHub Actions)

You can trigger a fresh test run directly from the GitHub interface:

1. Navigate to the **Actions** tab at the top of this repository  
2. Select the **API Automation CI** workflow from the left sidebar  
3. Click **Run workflow** and confirm  

The pipeline will:
- Build the Docker environment  
- Execute all **13 automated tests**  
- Publish the updated report automatically to GitHub Pages  

---

## 🏗 Project Architecture & Design Patterns

The framework follows a **decoupled, modular architecture** to ensure long-term maintainability and easy extension.

### 📁 Package Structure

- **clients**  
  Contains `BookClient` and `AuthorClient`  
  - Abstraction layer for API endpoints  
  - Uses Allure `@Step` annotations for readable execution logs

- **models**  
  Implements POJOs for `Book` and `Author` entities  
  - **Lombok** for boilerplate reduction  
  - **Jackson** for high-performance JSON serialization/deserialization

- **specs**  
  Contains the `SpecFactory`, which centralizes the `RequestSpecification`  
  - Global headers, base URI, and logging  
  - Allure filters automatically capture every request and response

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
```bash
sudo rm -rf allure-results && docker run --rm   -v "$(pwd)/allure-results:/app/target/allure-results"   bookstore-automation-ata
```

---

## 📊 Advanced Reporting – Allure

The framework generates **rich, interactive Allure reports**.

### View the Report Locally
```bash
allure serve allure-results
```

---

## 🛠 Tech Stack

- **Language:** Java 21  
- **API Library:** Rest-Assured 5.5.6  
- **Test Engine:** TestNG 7.11.0  
- **Reporting:** Allure 2.32.0  
- **Infrastructure:** Docker & GitHub Actions

---

## 🎯 Key Highlights

- Real-world API automation architecture  
- Clean separation of concerns  
- Dockerized execution  
- Enterprise-level reporting  
- Interview-ready project structure  

This project reflects **how API automation is implemented in real production teams**, not just demo examples.
