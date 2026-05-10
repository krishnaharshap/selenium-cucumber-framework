@E2E @Regression @Smoke
Feature: End-to-End Checkout Process
  As a customer
  I want to complete the full purchase flow
  So that I can successfully order products

  @Critical @Positive
  Scenario: Complete end-to-end purchase flow
    Given user is on the login page
    When user logs in with username "users.standard.username" and password "users.valid.password"
    Then user should be redirected to products page
    When user adds Sauce Labs Backpack to cart
    And user adds Sauce Labs Bike Light to cart
    And cart badge should show "2" items
    And user clicks on shopping cart icon
    Then cart page should be displayed
    And user should see 2 items in the cart
    When user completes the checkout with first name "checkout.primary.firstName", last name "checkout.primary.lastName", and postal code "checkout.primary.postalCode"
    Then order should be confirmed successfully
    And user should see confirmation message "messages.checkout.confirmation"

  @Critical @Positive @Dryrun
  Scenario: Purchase single item with complete checkout
    Given user is on the login page
    When user logs in with username "users.standard.username" and password "users.valid.password"
    And user adds Sauce Labs Backpack to cart
    And user clicks on shopping cart icon
    Then user should see 1 items in the cart
    When user clicks on checkout button
    And user enters checkout information with first name "checkout.secondary.firstName", last name "checkout.secondary.lastName", and postal code "checkout.secondary.postalCode"
    And user clicks continue button
    And user clicks finish button
    Then order should be confirmed successfully

  @Positive
  Scenario Outline: Complete purchase with different user data
    Given user is on the login page
    When user logs in with username "users.standard.username" and password "users.valid.password"
    And user adds Sauce Labs Backpack to cart
    And user clicks on shopping cart icon
    When user completes the checkout with first name "<firstName>", last name "<lastName>", and postal code "<postalCode>"
    Then order should be confirmed successfully

    Examples:
      | firstName                    | lastName                    | postalCode                    |
      | checkout.customer1.firstName | checkout.customer1.lastName | checkout.customer1.postalCode |
      | checkout.customer2.firstName | checkout.customer2.lastName | checkout.customer2.postalCode |
      | checkout.customer3.firstName | checkout.customer3.lastName | checkout.customer3.postalCode |
