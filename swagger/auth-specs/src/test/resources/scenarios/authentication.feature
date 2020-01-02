Feature: Authentication

  Background:
    Given try to authenticate

  Scenario:
    Given I have user credentials
    When I POST it to the /authenticate endpoint
    Then I receive a 200 status code