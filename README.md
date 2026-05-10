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

Maintainer: Krishna Harsha
