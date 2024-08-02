package io.codeka.glue;

import io.cucumber.java.en.When;

import static io.gatling.javaapi.core.CoreDsl.pause;

public class PauseStepDefinitions {

    private final CucumberGatlingScenarioBuilderHelper helper;

    public PauseStepDefinitions(CucumberGatlingScenarioBuilderHelper helper) {
        this.helper = helper;
    }

    @When("pause of {int} second(s)")
    public void pauseOf(int seconds) {
        helper.addPause(pause(seconds));
    }

    @When("pause between {int} and {int} seconds")
    public void pauseBetween(int minSeconds, int maxSeconds) {
        helper.addPause(pause(minSeconds, maxSeconds));
    }

}
