# E2E Test Automation Framework

This repository contains a Selenium, Cucumber, and TestNG based automation framework for the SauceDemo sample application. It supports UI testing, a small API testing slice, configurable browser execution, and report generation for local and CI runs.

## Scope

- UI coverage for login, product, cart, and checkout flows.
- API coverage using RestAssured.
- Page Object Model structure for maintainable page and step logic.
- Config-driven execution for browser, URL, waits, and reporting.
- GitHub Actions and Jenkins support.
- Failure screenshots and report artifacts.

## Current Test Inventory

| Area | Location | Notes |
| --- | --- | --- |
| Login UI | `src/test/resources/features/Login.feature` | Valid and negative login coverage |
| Product UI | `src/test/resources/features/ProductPurchase.feature` | Cart actions and product count checks |
| Checkout UI | `src/test/resources/features/E2ECheckout.feature` | End-to-end purchase flow |
| API | `src/test/resources/features/ApiTesting.feature` | Basic GET and POST checks |

The exact scenario counts are kept in the feature files and can change as the suite grows.

## Stack
[![CI - Maven Smoke Tests](https://github.com/krishnaharshap/selenium-cucumber-framework/actions/workflows/ci.yml/badge.svg?branch=develop)](https://github.com/krishnaharshap/selenium-cucumber-framework/actions/workflows/ci.yml)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)]()
[![Java](https://img.shields.io/badge/Java-11-orange)]()
[![Selenium](https://img.shields.io/badge/Selenium-4.15.0-green)]()
[![Cucumber](https://img.shields.io/badge/Cucumber-7.14.0-brightgreen)]()

**Enterprise-grade test automation framework implementing Page Object Model (POM) design pattern with Behavior-Driven Development (BDD) using Cucumber, Selenium WebDriver, and TestNG. Features dual reporting mechanisms (Allure & Extent), Jenkins CI/CD integration, and cross-browser testing capabilities.**

## Table of Contents

- [Project Overview](#project-overview)
- [Key Features](#key-features)
- [Technology Stack](#technology-stack)
- [Project Architecture](#project-architecture)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [CI/CD Integration](#cicd-integration)
- [Test Coverage](#test-coverage)
- [Best Practices](#best-practices)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [Documentation](#documentation)
- [License](#license)
- [Contact](#contact)

---

## Project Overview

### Objective
Develop a robust, scalable, and maintainable end-to-end test automation solution that reduces manual testing effort by 95%, accelerates release cycles, and improves software quality with 85% functional coverage.

### Application Under Test
**SauceDemo** - E-commerce Web Application  
[https://www.saucedemo.com](https://www.saucedemo.com)

### Project Metrics
- **Build Health:** Reported by the GitHub Actions CI badge above
- **CI Scope:** Chrome headless smoke suite on push and pull request
- **Test Scenarios:** 14+ UI scenarios across login, product, and checkout flows
- **Reports:** Surefire, Cucumber, screenshots, and Allure results uploaded as workflow artifacts

---

## Key Features

### Core Capabilities
-  **Page Object Model (POM)** - Industry-standard design pattern for maintainability
-  **Behavior-Driven Development (BDD)** - Cucumber with Gherkin syntax for business readability
-  **Dual Reporting** - Allure Reports (technical) + Extent Reports (executive)
-  **Cross-Browser Testing** - Chrome, Firefox, Edge with headless mode support
-  **Parallel Execution** - ThreadSafe WebDriver management for concurrent test runs
-  **CI/CD Ready** - Complete Jenkins pipeline integration with parameterized builds
-  **Smart Waits** - Explicit wait strategies eliminating test flakiness
-  **Screenshot Capture** - Automatic screenshot on failure with report integration
-  **Comprehensive Logging** - Log4j2 with console and file appenders
-  **Data-Driven Testing** - Externalized test data and configuration management
-  **Rerun Failed Tests** - Automatic failed scenario retry mechanism
-  **Tag-Based Execution** - Flexible test suite organization (@Smoke, @Regression, @E2E)

---

Selenium, Cucumber, and TestNG based end-to-end test automation framework for the SauceDemo sample e-commerce application. The framework uses the Page Object Model, reusable step definitions, configurable browser execution, and generated test reports for local and CI runs.

## Current Scope

- UI automation for SauceDemo login, product/cart, and checkout flows.
- Cucumber BDD feature files with TestNG execution.
- Page Object Model classes for screen-level behavior.
- Config-driven browser, URL, wait, and reporting settings.
- Screenshot capture for failed scenarios.
- Allure, Extent, Cucumber HTML, JSON, and JUnit report outputs.
- Jenkins pipeline and GitHub Actions workflow support.

## Application Under Test

SauceDemo: https://www.saucedemo.com/

The credentials used in this project are public demo credentials provided by SauceDemo. They are test data, not private secrets.

## Technology Stack

| Area | Tool |
| --- | --- |
| Language | Java |
| Build | Maven |
| UI automation | Selenium WebDriver |
| BDD | Cucumber JVM |
| Test runner | TestNG |
| API testing | RestAssured |
| Driver management | WebDriverManager |
| Reporting | Allure, Extent, Cucumber HTML/JSON/JUnit |
| Logging | Log4j2 |
| CI/CD | GitHub Actions, Jenkins |

Version numbers are defined in `pom.xml`.

## Configuration

Primary config file:

```text
src/main/resources/config/config.properties
```

Common settings:

```properties
browser=chrome
headless=false
url=https://www.saucedemo.com/
implicit.wait=10
explicit.wait=20
page.load.timeout=30
take.screenshot.on.failure=true
api.base.url=https://jsonplaceholder.typicode.com
```

## Running Tests

Compile the project:

```bash
mvn clean compile
```

Run all tests:

```bash
mvn clean test
```

Run UI smoke tests:

```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```

Run API scenarios:

```bash
mvn clean test -Dcucumber.filter.tags="@API"
```

Run headless:

```bash
mvn clean test -Dheadless=true
```

## Reports

| Report | Output |
| --- | --- |
| Cucumber HTML | `test-output/cucumber-reports/cucumber.html` |
| Cucumber JSON | `test-output/cucumber-reports/cucumber.json` |
| Cucumber JUnit XML | `test-output/cucumber-reports/cucumber.xml` |
| Allure results | `target/allure-results/` |
| Allure report | `target/site/allure-maven-plugin/index.html` |
| Extent report | `test-output/extent-reports/` |
| Screenshots | `test-output/screenshots/` |

Generate an Allure report:

```bash
mvn allure:report
```

Serve an Allure report locally:

#### Features:
-  Interactive HTML dashboard
-  Test execution trends and history
-  Step-by-step execution breakdown
-  Screenshot attachments
-  Timeline and duration metrics
-  Categorization by severity and features
-  Environment information

### Extent Reports

**Report Location:** `test-output/extent-reports/ExtentReport_[timestamp].html`

#### Features:
-  Executive summary dashboard
-  Pass/Fail statistics with charts
-  Pie charts and visual representations
-  Detailed error logs
-  Embedded screenshots
-  System information
-  Customizable themes

### Cucumber Reports

**Report Location:** `test-output/cucumber-reports/cucumber.html`

#### Features:
-  Scenario-wise execution details
-  Step-level pass/fail status
-  Execution duration
-  Feature-wise grouping

### Report Comparison

| Feature | Allure | Extent | Cucumber |
|---------|--------|--------|----------|
| Visual Dashboard | ✅ | ✅ | ❌ |
| Historical Trends | ✅ | ❌ | ❌ |
| Screenshots | ✅ | ✅ | ✅ |
| Step Details | ✅ | ✅ | ✅ |
| Charts/Graphs | ✅ | ✅ | ❌ |
| Executive Summary | ✅ | ✅ | ❌ |

---

## CI/CD Integration

### GitHub Actions

**File:** `.github/workflows/ci.yml`

The primary CI workflow runs a Chrome headless smoke suite on pushes to long-lived and review branches, and on pull requests into `develop` or `main`.

```bash
mvn -B -ntp test -Dbrowser=chrome -Dheadless=true "-Dcucumber.filter.tags=@Smoke"
```

Workflow artifacts include Surefire reports, Cucumber reports, screenshots, and Allure results when available.

### Jenkins Pipeline

**File:** `Jenkinsfile`

#### Pipeline Stages:
1. **Checkout** - Clone repository from SCM
2. **Clean** - Remove previous build artifacts
3. **Compile** - Compile source code
4. **Run Tests** - Execute test suite with parameters
5. **Generate Reports** - Create Allure and Extent reports
6. **Post Actions** - Publish reports and send notifications

#### Parameterized Build

| Parameter | Type | Options | Description |
|-----------|------|---------|-------------|
| BROWSER | Choice | chrome, firefox, edge | Browser for execution |
| TAGS | Choice | @Smoke, @Regression, @E2E | Test tags to execute |
| HEADLESS | Boolean | true, false | Headless mode toggle |

#### Jenkins Setup

```groovy
pipeline {
    agent any
    
    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'])
        choice(name: 'TAGS', choices: ['@Smoke', '@Regression', '@E2E'])
        booleanParam(name: 'HEADLESS', defaultValue: false)
    }
    
    stages {
        stage('Run Tests') {
            steps {
                bat "mvn clean test -Dbrowser=${BROWSER} -Dcucumber.filter.tags=${TAGS}"
            }
        }
    }
}
```

#### Report Publishing
- Allure Reports published via Allure Jenkins Plugin
- Extent Reports published via HTML Publisher Plugin
- Cucumber Reports archived as build artifacts

#### Email Notifications
- Success emails with report links
- Failure emails with console output
- Configurable recipient list

### Scheduled Builds
```groovy
triggers {
    cron('H 2 * * 1-5')  // Nightly at 2 AM, weekdays
}
```

---

## Test Coverage

### Modules Automated

#### 1. Login Module (6 Scenarios)
-  Valid login with standard user
-  Invalid username validation
-  Invalid password validation
-  Locked user handling
-  Step-by-step login flow
-  Data-driven login scenarios

#### 2. Product Management (5 Scenarios)
-  Add single product to cart
-  Add multiple products to cart
-  Remove product from cart
-  View cart with products
-  Product count validation

#### 3. Checkout Process (3 Scenarios)
-  Complete E2E purchase flow
-  Single item checkout
-  Multi-user checkout validation

### Coverage Metrics

| Metric | Value |
|--------|-------|
| **Total Scenarios** | 14+ |
| **Functional Coverage** | Track through CI artifacts and test inventory |
| **Critical Path Coverage** | Login, cart, and checkout smoke paths |
| **Pass Rate** | Reported by GitHub Actions and Jenkins runs |
| **Automated vs Manual** | Review per release scope |

---

## Best Practices

### Code Quality
-  Follow SOLID principles
-  Implement DRY (Don't Repeat Yourself)
-  Use meaningful variable and method names
-  Add comments for complex logic
-  Maintain consistent code formatting

### Test Design
-  Write independent test scenarios
-  Use appropriate wait strategies
-  Avoid hard-coded values
-  Implement proper exception handling
-  Keep tests atomic and focused

### Framework Maintenance
-  Regular dependency updates
-  Code review for all changes
-  Refactor duplicate code
-  Update documentation
-  Monitor test flakiness

### Naming Conventions

```java
// Page Objects
public class LoginPage extends BasePage {}

// Step Definitions
@Given("user is on the login page")
public void userIsOnTheLoginPage() {}

// Feature Files
Feature: User Login Functionality
  Scenario: Successful login with valid credentials
```

---

## Troubleshooting

### Common Issues & Solutions

#### Issue 1: Maven Dependencies Not Downloading
```bash
# Solution
mvn dependency:purge-local-repository
mvn clean install -U
```

#### Issue 2: WebDriver Not Found
```bash
mvn allure:serve
```

## CI/CD

GitHub Actions workflow:

```text
.github/workflows/ci.yml
```

The workflow is intended to run a headless Chrome smoke suite on pushes and pull requests.

Jenkins pipeline:

```text
Jenkinsfile
```

The Jenkins pipeline supports browser, tag, and headless parameters.

## Realistic Metrics

This README avoids fixed pass-rate and coverage claims. Use the latest workflow run and report artifacts for:

| Metric | Source |
| --- | --- |
| Build health | GitHub Actions and Jenkins run results |
| Scenario results | Cucumber, Allure, Extent, and Surefire outputs |
| Execution duration | CI job timestamps |
| Failed scenario evidence | Screenshots and rerun output |

## Repository Layout

```text
selenium-cucumber-framework/
├── .github/workflows/ci.yml
├── Jenkinsfile
├── pom.xml
├── testng.xml
├── src/main/java/com/automation/
├── src/main/resources/
└── src/test/
```

## Notes

- Keep generated reports, screenshots, logs, and local IDE files out of Git.
- Keep branch names short and descriptive.
- Update this README when the framework structure or CI flow changes.

**Last Updated:** May 2026  
**Version:** 1.0.0  
**Status:** Active
**Maintainer:** Krishna Harsha
