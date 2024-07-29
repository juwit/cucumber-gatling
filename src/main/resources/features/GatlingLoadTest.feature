Feature: Load test

  Scenario: First Gatling Scenario
    Given 2 constant users per second during 10 seconds
    When http request "https://gatling.io"

  Scenario: Scenario with pauses
    Given 1 user at once
    When http request "https://gatling.io"
    When pause between 3 and 10 seconds
    When http request "https://gatling.io"
    When pause of 1 second
    When http request "https://gatling.io"

  Scenario: Scenario with multiple injection steps
    Given 1 user at once
    And 2 users at once
    When http request "https://gatling.io"
