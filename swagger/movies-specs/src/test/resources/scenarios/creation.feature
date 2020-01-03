Feature: Creation of users

  Background:
    Given there is a User server

  Scenario: create a user
    Given I have a user payload
    When I POST it to the /users endpoint with invalid token
    Then I receive a 401 status code
