Feature: Step Definitions test

  # common step, to have at least one user for each scenario
  Background:
    Given 1 user at once

  Scenario: Users Injection Step Definitions
    Given 2 constant users per second during 10 seconds
    And 2 randomized constant users per second during 10 seconds
    Given no user for 10 seconds
    And 2 users at once

  Scenario: Pause Step Definitions
    When pause between 2 and 4 seconds
    And pause of 1 second
    And pause of 2 seconds

  Scenario: Feed Step Definitions
    Given random data from the csv file "data/file.csv"

  Scenario: HTTP Request Step Definitions
    When http request to url "https://pokeapi.co/api/v2/pokemon/${poke}" with parameters
      | type   | name      | value         |
      | header | X-API-KEY | dummy API key |
      | query  | poke      | ditto         |
