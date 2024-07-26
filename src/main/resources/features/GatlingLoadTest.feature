Feature: Load test

  Scenario: First Gatling Scenario
    Given 2 constant users per second during 10 seconds
    When http request "https://gatling.io"

  Scenario: Scenario with pauses
    Given 1 user
    When http request "https://gatling.io"
    When pause between 3 and 10 seconds
    When http request "https://gatling.io"
    When pause 1 seconds
    When http request "https://gatling.io"
