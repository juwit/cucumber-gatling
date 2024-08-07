Feature: Gatling Integration Test

  @GatlingIntegrationTest
  Scenario: Cucumber integrates with Gatling
    Given 1 user at once
    When http request "https://gatling.io"
