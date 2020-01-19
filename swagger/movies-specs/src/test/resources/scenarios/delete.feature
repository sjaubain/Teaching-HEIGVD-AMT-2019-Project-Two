Feature: Delete my ratings

  Background:

    Given I want to rate a movie with description "Good" and rate 4
    Then I POST my rating to the /movies/4 endpoint
    Then I receive a 201 status code

    When I go to /movies/4 path
    Then I see my last posted/put rating

  Scenario:

    When I DELETE my last rating to the movies/my-ratings endpoint
    When I go to /movies/42 path
    Then I don't see my last posted/put rating
