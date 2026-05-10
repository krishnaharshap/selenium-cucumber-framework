# E2E Test Automation Framework

[![CI - Maven Java Tests](https://github.com/krishnaharshap/selenium-cucumber-framework/actions/workflows/ci.yml/badge.svg?branch=develop)](https://github.com/krishnaharshap/selenium-cucumber-framework/actions/workflows/ci.yml)

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

```text
feat: add api smoke test coverage
fix: stabilize checkout wait condition
test: externalize saucedemo test data
chore: refresh framework dependencies
docs: update ci usage notes
```

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
