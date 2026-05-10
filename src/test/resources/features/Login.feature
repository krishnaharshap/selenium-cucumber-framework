@Login @Regression
Feature: User Login Functionality
  As a user of the SauceDemo application
  I want to be able to login with valid credentials
  So that I can access the products page

  Background:
    Given user is on the login page

  @Smoke @Positive
  Scenario: Successful login with valid credentials
    When user logs in with username "users.standard.username" and password "users.valid.password"
    Then user should be redirected to products page
    And products page title should be "pages.products.title"

  @Positive
  Scenario: Successful login using step by step approach
    When user enters username "users.standard.username"
    And user enters password "users.valid.password"
    And user clicks on login button
    Then user should be redirected to products page
    And user should see 6 products on the page

  @Negative
  Scenario: Login with invalid username
    When user logs in with username "users.invalid.username" and password "users.valid.password"
    Then user should see error message "messages.login.invalid"

  @Negative
  Scenario: Login with invalid password
    When user logs in with username "users.standard.username" and password "users.invalid.password"
    Then user should see error message "messages.login.invalid"

  @Negative
  Scenario: Login with locked out user
    When user logs in with username "users.locked.username" and password "users.valid.password"
    Then user should see error message "messages.login.locked"

  @Negative
  Scenario Outline: Login with multiple invalid credentials
    When user logs in with username "<username>" and password "<password>"
    Then user should see error message "<error_message>"

    Examples:
      | username                | password               | error_message          |
      | users.invalid.username  | users.valid.password   | messages.login.invalid |
      | users.standard.username | users.invalid.password | messages.login.invalid |
      | users.locked.username   | users.valid.password   | messages.login.locked  |
