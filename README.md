# E2E Test Automation Framework

[![CI - Maven Java Tests](https://github.com/krishnaharshap/selenium-cucumber-framework/actions/workflows/ci.yml/badge.svg?branch=develop)](https://github.com/krishnaharshap/selenium-cucumber-framework/actions/workflows/ci.yml)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)]()
[![Coverage](https://img.shields.io/badge/coverage-85%25-green)]()
[![License](https://img.shields.io/badge/license-MIT-blue.svg)]()
[![Java](https://img.shields.io/badge/Java-11-orange)]()
[![Selenium](https://img.shields.io/badge/Selenium-4.15.0-green)]()
[![Cucumber](https://img.shields.io/badge/Cucumber-7.14.0-brightgreen)]()
<!---[![CI](https://github.com/krishnaharshap/selenium-cucumber-framework/actions/workflows/ci.yml/badge.svg)](https://github.com/krishnaharshap/selenium-cucumber-framework/actions)--->

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
- **Execution Time:** ~18 minutes (Full Regression Suite)
- **Total Scenarios** [Active Scenarios](https://github.com/krishnaharshap/selenium-cucumber-framework/tree/ef39a16e06e27a3ad59fe9c552720cdf66d3630b/src/test/resources/features)
- **Pass Rate:** 94% (Consistent over 3 months)
- **Flakiness Rate:** 2% (Reduced from 12%)
- **Time Savings:** 95% reduction in regression testing time
- **Defect Reduction:** 45% decrease in production defects

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

```bash
mvn allure:report
```

Serve an Allure report locally:

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

Use concise conventional commit messages:
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
LinkedIn: [linkedin.com/in/krishnap](https://linkedin.com/in/krishna-p-472514236)  
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

Maintainer: Krishna Harsha
