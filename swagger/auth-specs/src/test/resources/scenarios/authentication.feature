Feature: Authentication

  Background:
    Given try to authenticate

  Scenario:

    Given I have invalid user credentials
    When I POST it to the /authentication endpoint
    Then I receive a 401 status code

    Given I have valid user credentials
    When I POST it to the /authentication endpoint
    Then I receive a 200 status code and a jwt
