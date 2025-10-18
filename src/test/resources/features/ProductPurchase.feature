@Product @Regression
Feature: Product Management and Cart Functionality
  As a logged-in user
  I want to add and remove products from cart
  So that I can manage my shopping cart

  Background:
    Given user is on the login page
    When user logs in with username "standard_user" and password "secret_sauce"
    Then user should be redirected to products page

  @Smoke @Positive
  Scenario: Add single product to cart
    When user adds Sauce Labs Backpack to cart
    Then item should be added to cart successfully
    And cart badge should show "1" items

  @Positive
  Scenario: Add multiple products to cart
    When user adds Sauce Labs Backpack to cart
    And user adds Sauce Labs Bike Light to cart
    Then cart badge should show "2" items

  @Positive
  Scenario: Remove product from cart
    When user adds Sauce Labs Backpack to cart
    And cart badge should show "1" items
    And user removes Sauce Labs Backpack from cart
    Then cart badge should show "0" items

  @Positive
  Scenario: View cart with added products
    When user adds Sauce Labs Backpack to cart
    And user adds Sauce Labs Bike Light to cart
    And user clicks on shopping cart icon
    Then cart page should be displayed
    And user should see 2 items in the cart

  @Positive
  Scenario: Verify products page displays all items
    Then user should see 6 products on the page