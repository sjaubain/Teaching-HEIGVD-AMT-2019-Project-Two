Feature: Creation of users

  Background:

    Given create a new user
    Given I have valid admin credentials
    When I POST it to the /authentication endpoint
    Then I receive a 200 status code and a jwt

  Scenario:

    When I POST a new user "test@gmail.com" and passord "test" to the /users endpoint
    Then I receive a 201 status code
    Then I get the new User to the /users/"test@gmail.com" path
    Then I receive a 200 status code

