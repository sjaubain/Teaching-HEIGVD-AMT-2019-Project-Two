Feature: Update a rating

  Background:

    Given I want to rate a movie with description "Arf.." and rate 1
    Then I POST my rating to the /movies/42 endpoint
    Then I receive a 201 status code

  Scenario:

    When I PUT my last rating to the movies/my-ratings endpoint with new description "Not so bad !"
    When I go to /movies/42 path
    Then My last posted/put rating has been updated