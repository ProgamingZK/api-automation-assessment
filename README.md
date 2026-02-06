# 📚 API Automation Framework – Online Bookstore

This repository contains a **professional-grade API automation framework** designed for the **Online Bookstore** system.

Developed by **Ata Pourfarivarnezhad**, a **Software Developer & SDET**, this project demonstrates **advanced API test automation practices** using **Java 21**, **Rest-Assured**, and **TestNG**.  
The framework is built with **scalability, maintainability, and real-world project standards** in mind.

---

## 🚀 CI/CD & Quick Access

This project is fully integrated with **GitHub Actions** and **GitHub Pages** for seamless delivery and monitoring.

### 📊 1. View Live Interactive Report

You can access the **real-time execution dashboard** without running any code locally.  
The report includes:
- Full Request / Response logs  
- Detailed execution timelines  

👉 **View Live Allure Report** https://atafarivar.github.io/api-automation-assessment/

---
### ⚙️ 2. Manual Test Execution (GitHub Actions)

You can trigger a fresh test run directly from the GitHub interface:

1. Navigate to the **Actions** tab  
2. Select the **API Automation CI** workflow  
3. Click **Run workflow**  

The pipeline will:
- Build the Docker environment  
- Execute all **20 automated tests**  
- Publish the updated Allure report

> [!NOTE]
> **Deployment Time:** While the tests execute in seconds, GitHub Pages may take **8-10 minutes** to process the new Allure data and update the live link. Please monitor the `pages-build-deployment` job in the Actions tab to confirm when the latest report is live.

---

## 🏗 Project Architecture & Design Patterns

The framework follows a **decoupled, modular architecture** to ensure long-term maintainability.

### 📁 Package Structure

- **clients**  
  Abstraction layer for API endpoints (`BookClient`, `AuthorClient`)  
  - Uses Allure `@Step` annotations for readable execution logs

- **models**  
  POJOs for `Book` and `Author` entities  
  - Jackson-based serialization / deserialization

- **specs**  
  Centralized `SpecFactory`  
  - Global headers  
  - Base URI  
  - Allure request/response filters

- **tests**  
  Organized test suites covering:
  - CRUD operations  
  - Performance validation  
  - Negative and resilience scenarios

---

## 💎 Senior-Level Technical Decisions

To meet **enterprise-grade expectations**, the following strategic decisions were implemented:

- **Environment Resilience (Constructor vs Builder)**  
  Although Lombok is used in the project, test data creation intentionally relies on **Constructor / Setter patterns**.  
  This avoids IDE-dependent annotation processing issues and guarantees **100% stability in CI and Docker environments**.

- **Contract Validation**  
  Every *Happy Path* test validates the `Content-Type` header to ensure strict adherence to the **JSON response contract**.

- **Fail-Fast Debugging**  
  Client requests include `.log().ifValidationFails()` to keep console output clean while still providing **actionable logs on failure**.

- **Performance SLA Tracking**  
  Hamcrest matchers assert that API response times remain within **business SLA limits (< 2 seconds)**.

---

## 🧪 Test Coverage & Scenarios

The framework contains **20 high-impact automated test cases** with a **100% pass rate**.

### 📘 Books API (11 Test Cases)

**Happy Path**
- Full CRUD operations (Create, Read, Update, Delete)

**Performance**
- Response time verification for global books list

**Negative & Edge Scenarios**
- Mass deletion protection (`DELETE` on base URL forbidden)
- Boundary validation for ID `0` → `404 Not Found`
- Payload validation for empty title values

---

### ✍️ Authors API (9 Test Cases)

**Business Logic**
- Comprehensive CRUD coverage for author entities

**Performance**
- SLA monitoring for author data retrieval

**Edge Cases**
- Non-existing ID handling (`404 Validation`)
- Input validation for empty author name fields

---

## 🐳 Docker Execution

The framework is fully containerized to guarantee **consistent execution across environments**.

### 1️⃣ Build the Docker Image
```bash
docker build -t bookstore-automation-ata .
```

### 2️⃣ Run with Automated Cleanup
### macOS / Linux
```bash
sudo rm -rf allure-results && docker run --rm   -v "$(pwd)/allure-results:/app/target/allure-results"   bookstore-automation-ata
```
### Windows (CMD / Command Prompt)
```bash
rd /s /q allure-results & docker run --rm -v "%cd%/allure-results:/app/target/allure-results" bookstore-automation-ata
```

---

## 📊 Advanced Reporting

View the report locally using Allure:
```bash
allure serve allure-results
```

---

## 🛠 Tech Stack

- **Language:** Java 21  
- **API Library:** Rest-Assured 5.5.x  
- **Test Engine:** TestNG 7.x  
- **Infrastructure:** Docker & GitHub Actions  

---

## 🎯 Final Notes

This project is intentionally designed to reflect **how API automation is built and maintained in real production teams**,  
not simplified demo examples.
