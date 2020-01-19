Feature: Rating Creation

  Scenario:

    Given I want to rate a movie with description "Bad" and rate 2
    Then I POST my rating to the /movies/12 endpoint
    Then I receive a 201 status code
