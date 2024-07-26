Feature: Load test

  Scenario: First Gatling Scenario
    Given 2 constant users per second during 10 seconds
    When http request "https://gatling.io"

  Scenario: Second Gatling Scenario
    Given 1 user
    When http request "https://gatling.io"
