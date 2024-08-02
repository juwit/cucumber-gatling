package io.codeka.glue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This Cucumber Glue (StepDefinition) that generates a Gatling Scenario as an output.
 * This class can be extended to allow adding your own definitions.
 */
public class CucumberHooksStepDefinitions {

    private static final Logger LOGGER = LoggerFactory.getLogger(CucumberHooksStepDefinitions.class.getName());

    private final CucumberGatlingScenarioBuilderHelper helper;

    public CucumberHooksStepDefinitions(CucumberGatlingScenarioBuilderHelper helper) {
        this.helper = helper;
    }

    /**
     * This hook re-creates a new Gatling scenario for the current Cucumber scenario, using the Cucumber scenario name.
     *
     * @param scenario the Cucumber scenario
     */
    @Before
    public void before(Scenario scenario) {
        LOGGER.info("Reading Gatling Scenario {}", scenario.getName());
        helper.newScenario(scenario.getName());
    }

    @After
    public void after(Scenario scenario) {
        LOGGER.info("Sending scenario {} to Gatling", scenario.getName());
        helper.buildScenario();
    }

}
