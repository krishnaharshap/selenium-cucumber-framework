# Selenium Cucumber Framework

[![CI](https://github.com/krishnaharshap/selenium-cucumber-framework/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/krishnaharshap/selenium-cucumber-framework/actions/workflows/ci.yml)
[![Cross-Browser](https://github.com/krishnaharshap/selenium-cucumber-framework/actions/workflows/cross-browser.yml/badge.svg?branch=main)](https://github.com/krishnaharshap/selenium-cucumber-framework/actions/workflows/cross-browser.yml)
[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.46.0-green)](https://www.selenium.dev/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.34.4-brightgreen)](https://cucumber.io/)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

End-to-end test automation framework built with Selenium 4, Cucumber 7, and TestNG. Designed as an AUT-agnostic, portfolio-quality framework that demonstrates real-world automation engineering practices: Page Object Model, Picocontainer dependency injection, multi-environment config, cross-browser CI, and automated Allure report publishing on GitHub Pages.

**Live Allure Report:** https://krishnaharshap.github.io/selenium-cucumber-framework/

---

## Stack

| Layer | Technology | Version |
|---|---|---|
| Language | Java | 17 |
| Build | Maven | 3.6+ |
| Browser automation | Selenium WebDriver | 4.46.0 |
| BDD | Cucumber JVM | 7.34.4 |
| Test runner | TestNG | 7.12 |
| Dependency injection | Picocontainer | via cucumber-picocontainer |
| Driver management | WebDriverManager | 6.3.4 |
| API testing | RestAssured | 6.0.1 |
| Reporting | Allure | 2.35.3 |
| Reporting (secondary) | ExtentReports | 5.1.2 |
| Logging | Log4j2 | 2.x |
| CI | GitHub Actions + Jenkins | — |

Exact versions are pinned in `pom.xml` properties.

---

## Project Structure

```
selenium-cucumber-framework/
├── .github/workflows/
│   ├── ci.yml                  # Smoke tests on push/PR (Chrome headless)
│   ├── cross-browser.yml       # Chrome + Firefox matrix on push/PR
│   ├── allure-gh-pages.yml     # Publishes Allure report to GitHub Pages
│   └── api-tests.yml           # API-only CI (Phase 2 branch)
├── Jenkinsfile
├── pom.xml
├── testng.xml
└── src/
    ├── main/java/com/automation/
    │   ├── constants/           # FrameworkConstants (paths, env)
    │   ├── pages/               # BasePage, LoginPage, ProductsPage, CheckoutPage
    │   └── utils/               # DriverManager, ConfigReader, WaitHelper, ScreenshotUtil
    ├── main/resources/
    │   ├── config/
    │   │   ├── config.properties          # Base config (defaults)
    │   │   └── environments/
    │   │       ├── qa.properties          # Overlays for QA (default)
    │   │       ├── staging.properties     # Overlays for staging
    │   │       └── prod.properties        # Overlays for prod (smoke only)
    │   └── log4j2.xml
    └── test/
        ├── java/com/automation/
        │   ├── context/         # ScenarioContext (DI shared state), ApiContext
        │   ├── listeners/       # AllureListener, ExtentListener (both TestNG)
        │   ├── runners/         # TestRunner, FailedTestRunner
        │   ├── stepdefinitions/ # Hooks, LoginSteps, ProductSteps, CheckoutSteps, ApiSteps
        │   └── utils/           # TestDataReader, ExtentManager
        └── resources/
            ├── features/
            │   ├── Login.feature
            │   ├── ProductPurchase.feature
            │   ├── E2ECheckout.feature
            │   └── ApiTesting.feature
            ├── testdata/
            │   ├── qa-test-data.properties
            │   └── saucedemo-test-data.properties
            ├── cucumber.properties        # Default tag: @Regression
            ├── allure.properties
            └── extent.properties
```

---

## Prerequisites

- Java 17 (check: `java -version`)
- Maven 3.6+ (check: `mvn -version`)
- Chrome, Firefox, or Edge installed locally for non-headless runs
- Git

---

## Run Locally

### 1. Clone and compile

```bash
git clone https://github.com/krishnaharshap/selenium-cucumber-framework.git
cd selenium-cucumber-framework
mvn clean compile
```

### 2. Run smoke tests (Chrome, headed)

```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```

### 3. Run smoke tests headless (matches CI exactly)

```bash
mvn clean test -Dbrowser=chrome -Dheadless=true -Dcucumber.filter.tags="@Smoke"
```

### 4. Run full regression suite

```bash
mvn clean test -Dcucumber.filter.tags="@Regression"
```

### 5. Run on a different browser

```bash
mvn clean test -Dbrowser=firefox -Dcucumber.filter.tags="@Smoke"
mvn clean test -Dbrowser=edge    -Dcucumber.filter.tags="@Smoke"
```

### 6. Run against a different environment

```bash
mvn clean test -Pstaging -Dcucumber.filter.tags="@Smoke"
mvn clean test -Pprod    -Dcucumber.filter.tags="@Smoke"
```

The `-P` profile loads the matching overlay from `src/main/resources/config/environments/`.
Default environment is `qa` (active by default, no flag needed).

### 7. Run API tests

```bash
mvn clean test -Dcucumber.filter.tags="@API"
```

### 8. Rerun only failed scenarios

After any test run, a rerun file is written to `test-output/cucumber-reports/rerun.txt`.

```bash
mvn clean test -Dcucumber.features="@test-output/cucumber-reports/rerun.txt"
```

### 9. View Allure report locally

```bash
mvn allure:report
mvn allure:serve
```

`allure:serve` opens a live browser tab. Results are in `target/allure-results/` after each run.

---

## Configuration

Primary config: `src/main/resources/config/config.properties`

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

Any property can be overridden at runtime with `-D`:

```bash
mvn clean test -Durl=https://your-app.com -Dbrowser=firefox -Dheadless=true
```

Environment-specific overrides go in `src/main/resources/config/environments/<env>.properties` and are layered on top of the base config at runtime.

---

## Test Inventory

| Area | Feature file | Scenarios |
|---|---|---:|
| Login | `Login.feature` | 6 |
| Products and cart | `ProductPurchase.feature` | 5 |
| End-to-end checkout | `E2ECheckout.feature` | 3 |
| API (Phase 2) | `ApiTesting.feature` | 2 |

Tags in use: `@Smoke`, `@Regression`, `@E2E`, `@Login`, `@Product`, `@API`, `@Positive`, `@Negative`

---

## CI/CD

### GitHub Actions — free for public repos

This repo is public. GitHub Actions gives **unlimited free minutes** on Linux runners (`ubuntu-latest`) for public repositories. There is no cost and no monthly cap. If you ever move this to a private repo, the free tier is 2,000 minutes/month on Linux.

Five workflows are active:

| Workflow | File | Trigger | What it runs |
|---|---|---|---|
| CI smoke | `ci.yml` | push / PR to main, develop | `@Smoke` on Chrome headless |
| Cross-browser | `cross-browser.yml` | push / PR to main, develop | `@Smoke` on Chrome + Firefox in parallel |
| Allure Pages | `allure-gh-pages.yml` | after CI smoke completes | Publishes Allure report to GitHub Pages |
| API tests | `api-tests.yml` | push to feat/api-scaffold | `@API` headless, no browser |
| Full regression | `regression.yml` | nightly (cron) + manual dispatch | Configurable tag filter (default `@Regression`) and browser (chrome/firefox), full report artifacts |

To trigger manually: **Actions** tab → select workflow → **Run workflow** → pick branch → Run.

### Allure report on GitHub Pages

After `ci.yml` completes, `allure-gh-pages.yml` fires automatically: it re-runs `@Smoke`, generates the report with the Allure CLI (`allure generate`), carries forward trend history from the current `gh-pages` branch, and publishes the result.

To activate on a fresh fork or after first run: **Settings → Pages → Source → Deploy from branch → `gh-pages` / root**. The `gh-pages` branch is created automatically the first time the workflow runs (via push to `main`/`develop` or manual dispatch from the Actions tab) — Pages can't be enabled until it exists.

Live URL: https://krishnaharshap.github.io/selenium-cucumber-framework/

### Jenkins (local or corporate)

The `Jenkinsfile` sets up a parameterized pipeline:

| Parameter | Options | Default |
|---|---|---|
| BROWSER | chrome, firefox, edge | chrome |
| TAGS | @Smoke, @Regression, @E2E, @Login, @Product | @Regression |
| HEADLESS | true / false | false |

Jenkins prerequisites: JDK-17 configured in Global Tool Configuration as `JDK-17`, Maven configured as `Maven-3.9.5`.

Plugins needed for full reporting: Allure Jenkins Plugin, HTML Publisher Plugin, Email Extension Plugin.

---

## Reports

| Report | Location after run |
|---|---|
| Cucumber HTML | `test-output/cucumber-reports/cucumber.html` |
| Cucumber JSON | `test-output/cucumber-reports/cucumber.json` |
| Allure results | `target/allure-results/` |
| Allure HTML | `target/site/allure-maven-plugin/index.html` (after `mvn allure:report`) |
| Extent report | `test-output/extent-reports/` |
| Failed rerun list | `test-output/cucumber-reports/rerun.txt` |
| Screenshots | `test-output/screenshots/` |

CI artifacts (Surefire reports, Cucumber reports, Allure results) are uploaded on every workflow run under **Actions → [run] → Artifacts**.

---

## Key Design Decisions

**Picocontainer DI** — `ScenarioContext` is injected once per scenario into all step definition classes by Picocontainer. All page objects live in the context, so `LoginSteps`, `ProductSteps`, and `CheckoutSteps` share the same page instances for a given scenario. No static state.

**AUT-agnostic config** — The framework is not hardwired to SauceDemo. Change `url` in `config.properties` or override with `-Durl=`. The generic `addProductToCart(String)` step derives button IDs from product names so product tests work against any standard e-commerce app.

**Parallel-ready** — `DriverManager` uses `ThreadLocal<WebDriver>`. To enable parallel execution, change `parallel="none"` to `parallel="methods"` in `testng.xml` and set a thread count. Leave at `none` until scenarios are confirmed flake-free.

**Cross-browser** — CI runs Chrome + Firefox in parallel via matrix. Edge is supported in `DriverManager` locally but excluded from the CI matrix (install complexity on ubuntu-latest).

---

## Contact

Krishna Harsha — krishnaharshap11@gmail.com  
GitHub: https://github.com/krishnaharshap
