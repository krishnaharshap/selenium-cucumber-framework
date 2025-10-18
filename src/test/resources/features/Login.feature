@Login @Regression
Feature: User Login Functionality
  As a user of the SauceDemo application
  I want to be able to login with valid credentials
  So that I can access the products page

  Background:
    Given user is on the login page

  @Smoke @Positive
  Scenario: Successful login with valid credentials
    When user logs in with username "standard_user" and password "secret_sauce"
    Then user should be redirected to products page
    And products page title should be "Products"

  @Positive
  Scenario: Successful login using step by step approach
    When user enters username "standard_user"
    And user enters password "secret_sauce"
    And user clicks on login button
    Then user should be redirected to products page
    And user should see 6 products on the page

  @Negative
  Scenario: Login with invalid username
    When user logs in with username "invalid_user" and password "secret_sauce"
    Then user should see error message "Epic sadface: Username and password do not match any user in this service"

  @Negative
  Scenario: Login with invalid password
    When user logs in with username "standard_user" and password "invalid_password"
    Then user should see error message "Epic sadface: Username and password do not match any user in this service"

  @Negative
  Scenario: Login with locked out user
    When user logs in with username "locked_out_user" and password "secret_sauce"
    Then user should see error message "Epic sadface: Sorry, this user has been locked out"

  @Negative
  Scenario Outline: Login with multiple invalid credentials
    When user logs in with username "<username>" and password "<password>"
    Then user should see error message "<error_message>"

    Examples:
      | username        | password      | error_message                                                             |
      | invalid_user    | secret_sauce  | Epic sadface: Username and password do not match any user in this service |
      | standard_user   | wrong_pwd     | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user | secret_sauce  | Epic sadface: Sorry, this user has been locked out                        |