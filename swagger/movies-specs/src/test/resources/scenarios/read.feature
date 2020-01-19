Feature: Reading my ratings

  Background:

    Given I want to rate a movie with description "Good" and rate 5
    Then I POST my rating to the /movies/42 endpoint
    Then I receive a 201 status code

  Scenario:

    When I go to /movies/42 path
    Then I see my last posted/put rating
    Then I receive a 200 status code