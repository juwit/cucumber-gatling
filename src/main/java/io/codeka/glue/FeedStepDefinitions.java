package io.codeka.glue;

import io.cucumber.java.en.Given;

import static io.gatling.javaapi.core.CoreDsl.*;

/**
 * Step definitions for feeding Gatling Scenario with data from a file
 */
public class FeedStepDefinitions {

    private final CucumberGatlingScenarioBuilderHelper helper;

    public FeedStepDefinitions(CucumberGatlingScenarioBuilderHelper helper) {
        this.helper = helper;
    }

    @Given("random data from the csv file {string}")
    public void randomDataFromCsvFile(String fileName) {
        helper.feed(csv(fileName));
    }

}
