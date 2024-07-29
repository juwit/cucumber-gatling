package io.codeka;

import io.codeka.glue.GatlingScenarioRegistry;
import io.cucumber.core.feature.FeatureWithLines;
import io.cucumber.core.feature.GluePath;
import io.cucumber.core.options.RuntimeOptions;
import io.cucumber.core.options.RuntimeOptionsBuilder;
import io.cucumber.core.runtime.Runtime;
import io.gatling.javaapi.core.Simulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class runs the cucumber features in order to build the Gatling Scenarios.
 * Gatling Scenarios are then used to setup the Gatling Simulation
 */
public class CucumberRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CucumberRunner.class.getName());

    public void setUpSimulation(Simulation gatlingSimulation) {
        LOGGER.info("Setting up Gatling Simulation using Cucumber Scenarios");

        LOGGER.info("Running Cucumber Scenarios");
        RuntimeOptions runtimeOptions = new RuntimeOptionsBuilder()
                .addGlue(GluePath.parse("io.codeka.glue"))
                .addDefaultSummaryPrinterIfNotDisabled()
                .addFeature(FeatureWithLines.parse("classpath:/features/"))
                .build();

        Runtime cucumberRuntime = Runtime.builder()
                .withRuntimeOptions(runtimeOptions)
                .build();
        cucumberRuntime.run();

        LOGGER.info("Building Gatling Simulations");
        gatlingSimulation.setUp(GatlingScenarioRegistry.getAllScenarios());
    }
}
