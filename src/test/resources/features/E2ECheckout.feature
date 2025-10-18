@E2E @Regression @Smoke
Feature: End-to-End Checkout Process
  As a customer
  I want to complete the full purchase flow
  So that I can successfully order products

  @Critical @Positive
  Scenario: Complete end-to-end purchase flow
    Given user is on the login page
    When user logs in with username "standard_user" and password "secret_sauce"
    Then user should be redirected to products page
    When user adds Sauce Labs Backpack to cart
    And user adds Sauce Labs Bike Light to cart
    And cart badge should show "2" items
    And user clicks on shopping cart icon
    Then cart page should be displayed
    And user should see 2 items in the cart
    When user completes the checkout with first name "John", last name "Doe", and postal code "12345"
    Then order should be confirmed successfully
    And user should see confirmation message "Thank you for your order!"

  @Critical @Positive @Dryrun
  Scenario: Purchase single item with complete checkout
    Given user is on the login page
    When user logs in with username "standard_user" and password "secret_sauce"
    And user adds Sauce Labs Backpack to cart
    And user clicks on shopping cart icon
    Then user should see 1 items in the cart
    When user clicks on checkout button
    And user enters checkout information with first name "Jane", last name "Smith", and postal code "54321"
    And user clicks continue button
    And user clicks finish button
    Then order should be confirmed successfully

  @Positive
  Scenario Outline: Complete purchase with different user data
    Given user is on the login page
    When user logs in with username "standard_user" and password "secret_sauce"
    And user adds Sauce Labs Backpack to cart
    And user clicks on shopping cart icon
    When user completes the checkout with first name "<firstName>", last name "<lastName>", and postal code "<postalCode>"
    Then order should be confirmed successfully

    Examples:
      | firstName | lastName  | postalCode |
      | Alice     | Johnson   | 10001      |
      | Bob       | Williams  | 90210      |
      | Charlie   | Brown     | 60601      |