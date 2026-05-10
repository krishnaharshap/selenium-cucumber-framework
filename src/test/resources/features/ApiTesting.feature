@API @Phase2
Feature: API Testing Integration
  As an automation engineer
  I want a lightweight API testing slice
  So that the framework can validate REST endpoints alongside UI journeys

  Scenario: Retrieve a post by id
    Given API base URL is configured
    When user sends a GET request to "/posts/1"
    Then API response status code should be 200
    And API response field "id" should be "1"
    And API response field "userId" should be "1"

  Scenario: Create a post
    Given API base URL is configured
    When user creates a post with title "phase 2 api test" and body "rest assured integration"
    Then API response status code should be 201
    And API response field "title" should be "phase 2 api test"
    And API response field "body" should be "rest assured integration"
