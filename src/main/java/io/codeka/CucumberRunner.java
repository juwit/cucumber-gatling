package io.codeka;

import io.codeka.glue.GatlingScenarioRegistry;
import io.cucumber.core.feature.FeatureWithLines;
import io.cucumber.core.feature.GluePath;
import io.cucumber.core.options.RuntimeOptions;
import io.cucumber.core.options.RuntimeOptionsBuilder;
import io.cucumber.core.runtime.Runtime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class runs the cucumber features in order to build the Gatling Scenarios.
 * Gatling Scenarios are then used to setup the Gatling Simulation
 */
public class CucumberRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CucumberRunner.class.getName());

    public boolean runCucumberFeature(String featurePath) {
        LOGGER.info("Running Cucumber Scenarios");
        RuntimeOptions runtimeOptions = new RuntimeOptionsBuilder()
                .addGlue(GluePath.parse("io.codeka.glue"))
                .addDefaultSummaryPrinterIfNotDisabled()
                .addFeature(FeatureWithLines.parse(featurePath))
                .build();

        Runtime cucumberRuntime = Runtime.builder()
                .withRuntimeOptions(runtimeOptions)
                .build();
        cucumberRuntime.run();

        if (cucumberRuntime.exitStatus() != 0) {
            LOGGER.error("Cucumber encountered an error during its execution, exiting");
        }
        return cucumberRuntime.exitStatus() == 0;
    }

    public void setUpSimulation(CucumberSimulation gatlingSimulation) {
        LOGGER.info("Setting up Gatling Simulation using Cucumber Scenarios");

        if (this.runCucumberFeature(gatlingSimulation.getFeaturePath())) {
            LOGGER.info("Building Gatling Simulations");
            gatlingSimulation.setUp(GatlingScenarioRegistry.getAllScenarios());
        }
    }
}
