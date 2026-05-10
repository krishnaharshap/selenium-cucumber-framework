# E2E Test Automation Framework

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
- ✅ **Page Object Model (POM)** - Industry-standard design pattern for maintainability
- ✅ **Behavior-Driven Development (BDD)** - Cucumber with Gherkin syntax for business readability
- ✅ **Dual Reporting** - Allure Reports (technical) + Extent Reports (executive)
- ✅ **Cross-Browser Testing** - Chrome, Firefox, Edge with headless mode support
- ✅ **Parallel Execution** - ThreadSafe WebDriver management for concurrent test runs
- ✅ **CI/CD Ready** - Complete Jenkins pipeline integration with parameterized builds
- ✅ **Smart Waits** - Explicit wait strategies eliminating test flakiness
- ✅ **Screenshot Capture** - Automatic screenshot on failure with report integration
- ✅ **Comprehensive Logging** - Log4j2 with console and file appenders
- ✅ **Data-Driven Testing** - Externalized test data and configuration management
- ✅ **Rerun Failed Tests** - Automatic failed scenario retry mechanism
- ✅ **Tag-Based Execution** - Flexible test suite organization (@Smoke, @Regression, @E2E)

---

## Technology Stack

| Area | Tool |
| --- | --- |
| Language | Java |
| Build | Maven |
| Browser automation | Selenium WebDriver |
| BDD | Cucumber JVM |
| Test runner | TestNG |
| Driver management | WebDriverManager |
| Reporting | Allure, Extent, Cucumber reports |
| Logging | Log4j2 |
| CI/CD | GitHub Actions, Jenkins |

Version numbers are managed in `pom.xml`. Check that file for the exact dependency versions used by the current branch.

## Test Inventory

| Area | Feature file | Scenario count |
| --- | --- | ---: |
| Login | `src/test/resources/features/Login.feature` | 6 |
| Products and cart | `src/test/resources/features/ProductPurchase.feature` | 5 |
| Checkout | `src/test/resources/features/E2ECheckout.feature` | 3 |

Current automated UI scenario count: 14.

## Realistic Metrics

This project does not hard-code pass rate, coverage percentage, or defect reduction claims in the README. Those numbers should come from the latest CI run, generated reports, or a release-specific quality summary.

Use these maintained signals instead:

| Metric | Source |
| --- | --- |
| Build health | GitHub Actions badge and workflow runs |
| Scenario results | Surefire, Cucumber, Allure, and Extent reports |
| Execution duration | CI job duration and report timestamps |
| Failed scenario evidence | Screenshots and Cucumber rerun output |

## Project Structure

```text
selenium-cucumber-framework/
├── .github/workflows/ci.yml
├── Jenkinsfile
├── pom.xml
├── testng.xml
├── src/main/java/com/automation/
│   ├── constants/
│   ├── pages/
│   └── utils/
├── src/main/resources/
│   ├── config/config.properties
│   └── log4j2.xml
└── src/test/
    ├── java/com/automation/
    │   ├── listeners/
    │   ├── runners/
    │   └── stepdefinitions/
    └── resources/
        ├── features/
        ├── allure.properties
        ├── extent-config.xml
        └── extent.properties
```

Generated directories such as `target/`, `test-output/`, `logs/`, report folders, and screenshots are ignored by Git.
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

## Test Inventory

| Area | Feature file | Scenario count |
| --- | --- | ---: |
| Login | `src/test/resources/features/Login.feature` | 6 |
| Products and cart | `src/test/resources/features/ProductPurchase.feature` | 5 |
| Checkout | `src/test/resources/features/E2ECheckout.feature` | 3 |

Current automated UI scenario count: 14.

## Realistic Metrics

This project does not hard-code pass rate, coverage percentage, or defect reduction claims in the README. Those numbers should come from the latest CI run, generated reports, or a release-specific quality summary.

Use these maintained signals instead:

| Metric | Source |
| --- | --- |
| Build health | GitHub Actions badge and workflow runs |
| Scenario results | Surefire, Cucumber, Allure, and Extent reports |
| Execution duration | CI job duration and report timestamps |
| Failed scenario evidence | Screenshots and Cucumber rerun output |

## Project Structure

```text
selenium-cucumber-framework/
├── .github/workflows/ci.yml
├── Jenkinsfile
├── pom.xml
├── testng.xml
├── src/main/java/com/automation/
│   ├── constants/
│   ├── pages/
│   └── utils/
├── src/main/resources/
│   ├── config/config.properties
│   └── log4j2.xml
└── src/test/
    ├── java/com/automation/
    │   ├── listeners/
    │   ├── runners/
    │   └── stepdefinitions/
    └── resources/
        ├── features/
        ├── allure.properties
        ├── extent-config.xml
        └── extent.properties
```

Generated directories such as `target/`, `test-output/`, `logs/`, report folders, and screenshots are ignored by Git.

## Prerequisites

- Java JDK 11 or newer.
- Maven 3.6 or newer.
- Chrome, Firefox, or Edge for local browser execution.
- Git.

CI currently uses a Temurin JDK through GitHub Actions.

## Configuration

Primary configuration file:

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
```

The suite supports command-line overrides where implemented by the framework and runner configuration.

## Running Tests Locally

Install dependencies and compile:

```bash
mvn clean compile
```

Run all configured tests:

```bash
mvn clean test
```

Run smoke scenarios:

```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```

Run regression scenarios:

```bash
mvn clean test -Dcucumber.filter.tags="@Regression"
```

Run with a browser selection:

```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

Run headless:

```bash
mvn clean test -Dheadless=true
```

#### Run Specific Feature
```bash
mvn clean test -Dcucumber.features="src/test/resources/features/Login.feature"
```

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
| Allure HTML report | `target/site/allure-maven-plugin/index.html` after `mvn allure:report` |
| Extent report | `test-output/extent-reports/` |
| Screenshots | `test-output/screenshots/` |

Generate an Allure report:

### Test Execution Strategies

| Strategy | Tag | Duration | Use Case |
|----------|-----|----------|----------|
| **Smoke** | `@Smoke` | 5-7 min | Quick validation on every commit |
| **Regression** | `@Regression` | 15-20 min | Complete validation before release |
| **E2E** | `@E2E` | 10-12 min | Business flow validation |
| **Positive** | `@Positive` | - | Happy path scenarios |
| **Negative** | `@Negative` | - | Error handling validation |

---

## Test Reports

### Allure Reports

#### Generate and View
```bash
mvn allure:report
```

Serve an Allure report locally:

#### Features:
- ✅ Interactive HTML dashboard
- ✅ Test execution trends and history
- ✅ Step-by-step execution breakdown
- ✅ Screenshot attachments
- ✅ Timeline and duration metrics
- ✅ Categorization by severity and features
- ✅ Environment information

### Extent Reports

**Report Location:** `test-output/extent-reports/ExtentReport_[timestamp].html`

#### Features:
- ✅ Executive summary dashboard
- ✅ Pass/Fail statistics with charts
- ✅ Pie charts and visual representations
- ✅ Detailed error logs
- ✅ Embedded screenshots
- ✅ System information
- ✅ Customizable themes

### Cucumber Reports

**Report Location:** `test-output/cucumber-reports/cucumber.html`

#### Features:
- ✅ Scenario-wise execution details
- ✅ Step-level pass/fail status
- ✅ Execution duration
- ✅ Feature-wise grouping

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
- ✅ Valid login with standard user
- ✅ Invalid username validation
- ✅ Invalid password validation
- ✅ Locked user handling
- ✅ Step-by-step login flow
- ✅ Data-driven login scenarios

#### 2. Product Management (5 Scenarios)
- ✅ Add single product to cart
- ✅ Add multiple products to cart
- ✅ Remove product from cart
- ✅ View cart with products
- ✅ Product count validation

#### 3. Checkout Process (3 Scenarios)
- ✅ Complete E2E purchase flow
- ✅ Single item checkout
- ✅ Multi-user checkout validation

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
- ✅ Follow SOLID principles
- ✅ Implement DRY (Don't Repeat Yourself)
- ✅ Use meaningful variable and method names
- ✅ Add comments for complex logic
- ✅ Maintain consistent code formatting

### Test Design
- ✅ Write independent test scenarios
- ✅ Use appropriate wait strategies
- ✅ Avoid hard-coded values
- ✅ Implement proper exception handling
- ✅ Keep tests atomic and focused

### Framework Maintenance
- ✅ Regular dependency updates
- ✅ Code review for all changes
- ✅ Refactor duplicate code
- ✅ Update documentation
- ✅ Monitor test flakiness

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
mvn allure:serve
```

## CI/CD

### GitHub Actions

Workflow file:

```text
.github/workflows/ci.yml
```

The workflow runs Maven tests for pushes and pull requests targeting the main development branches. Test reports and build artifacts should be reviewed from the workflow run rather than represented as static README claims.

### Jenkins

Pipeline file:

```text
Jenkinsfile
```

The Jenkins pipeline supports parameterized browser, tag, and headless execution, then publishes available test reports and archives artifacts.

## Branching and Commit Convention

Use small branches with clear ownership:
#### Issue 2: WebDriver Not Found
```bash
mvn allure:serve
```

## CI/CD

### GitHub Actions

Workflow file:

```text
.github/workflows/ci.yml
```

The workflow runs Maven tests for pushes and pull requests targeting the main development branches. Test reports and build artifacts should be reviewed from the workflow run rather than represented as static README claims.

### Jenkins

Pipeline file:

```text
Jenkinsfile
```

The Jenkins pipeline supports parameterized browser, tag, and headless execution, then publishes available test reports and archives artifacts.

## Branching and Commit Convention

Use small branches with clear ownership:

```bash
feature/<short-description>
test/<short-description>
chore/<short-description>
docs/<short-description>
fix/<short-description>
```

Copyright (c) 2024 Krishna Harsha

```text
feat: add api smoke test coverage
fix: stabilize checkout wait condition
test: externalize saucedemo test data
chore: refresh framework dependencies
docs: update ci usage notes
```

---

## Contact

### Project Maintainer
**Krishna Harsha**  
Email: krishnaharshap11@gmail.com  
LinkedIn: [linkedin.com/in/krishnap](https://linkedin.com/in/krishnap)  
GitHub: [github.com/krishnaharshap](https://github.com/krishnaharshap)

### Support Channels
- **Bug Reports:** [GitHub Issues](https://github.com/krishnaharshap/selenium-cucumber-framework/issues)
- **Feature Requests:** [GitHub Discussions](https://github.com/krishnaharshap/selenium-cucumber-framework/discussions)
- **Email:** krishnaharshap11@gmail.com

---

## Acknowledgments

- **SauceDemo** for providing the test application
- **Selenium Community** for excellent documentation and support
- **Cucumber Team** for the BDD framework
- **Allure & Extent** teams for reporting solutions
- **Open Source Community** for continuous improvements

---

## Project Insight & Community

![Stars](https://img.shields.io/github/stars/krishnaharshap/selenium-cucumber-framework?style=social)  
![Forks](https://img.shields.io/github/forks/krishnaharshap/selenium-cucumber-framework?style=social)  
![Issues](https://img.shields.io/github/issues/krishnaharshap/selenium-cucumber-framework)  
![Pull Requests](https://img.shields.io/github/issues-pr/krishnaharshap/selenium-cucumber-framework)

> Help us build **together** — see community metrics & learn how to contribute:  
[🔗 Community Graph & Contribution Guide](https://github.com/krishnaharshap/selenium-cucumber-framework/graphs/community)

---

## Roadmap

Planned work should stay scoped and merge in reviewable increments:

- API testing integration with RestAssured.
- Dependency and reporting cleanup.
- Improved CI smoke execution and artifact publishing.
- Test data cleanup and externalization.

Avoid documenting mobile, visual, performance, database, or container automation as implemented until those capabilities exist in the codebase.

## Maintenance Notes

- Keep README claims tied to files, commands, or generated reports.
- Do not commit generated reports, browser artifacts, screenshots, logs, local IDE files, or secrets.
- Prefer small pull requests that can be checked independently by CI.
- Update this README when framework behavior, CI commands, or report paths change.

## Repository

GitHub: https://github.com/krishnaharshap/selenium-cucumber-framework

**Last Updated:** May 2026  
**Version:** 1.0.0  
**Status:** Active
**Maintainer:** Krishna Harsha
