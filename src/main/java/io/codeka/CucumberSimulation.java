package io.codeka;

import io.gatling.javaapi.core.Simulation;

/**
 * This class is a Gatling Simulation, that reads a Gherkin file !
 */
public class CucumberSimulation extends Simulation {

    public CucumberSimulation() {
        // run cucumber to build the scenarios
        var cucumberRunner = new CucumberRunner();
        cucumberRunner.setUpSimulation(this);
    }

    public String getFeaturePath() {
        return "classpath:/features/";
    }

}
