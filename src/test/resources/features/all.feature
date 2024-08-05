Feature: Step Definitions test

  Scenario: Users Injection Step Definitions
    Given 2 constant users per second during 10 seconds
    And 2 randomized constant users per second during 10 seconds
    Given no user for 10 seconds
    Given 1 user at once
    And 2 users at once

  Scenario: Pause Step Definitions
    When pause between 2 and 4 seconds
    And pause of 1 second
    And pause of 2 seconds

  Scenario: Feed Step Definitions
    Given random data from the csv file "file.csv"
