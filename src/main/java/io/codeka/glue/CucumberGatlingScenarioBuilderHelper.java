package io.codeka.glue;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.OpenInjectionStep;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.exec.Executable;

import java.util.ArrayList;
import java.util.List;

import static io.gatling.javaapi.core.CoreDsl.scenario;

/**
 * Class that helps building a Gatling Scenario from Cucumber code.
 */
public class CucumberGatlingScenarioBuilderHelper {

    private final GatlingScenarioRegistry gatlingScenarioRegistry;
    private ScenarioBuilder scenarioBuilder;
    private List<OpenInjectionStep> injectionSteps;

    public CucumberGatlingScenarioBuilderHelper(GatlingScenarioRegistry gatlingScenarioRegistry) {
        this.gatlingScenarioRegistry = gatlingScenarioRegistry;
    }

    /**
     * Initialized the build of a new scenario with the given name.
     * Old scenario configuration will be lost.
     *
     * @param scenarioName the name of the new scenario
     */
    public void newScenario(String scenarioName) {
        scenarioBuilder = scenario(scenarioName);
        this.injectionSteps = new ArrayList<>();
    }

    public void buildScenario() {
        this.gatlingScenarioRegistry.add(this.scenarioBuilder.injectOpen(this.injectionSteps));
    }

    public void addInjectionStep(OpenInjectionStep injection) {
        this.injectionSteps.add(injection);
    }

    public void addStep(Executable httpRequest) {
        this.scenarioBuilder = this.scenarioBuilder.exec(httpRequest);
    }

    public void addPause(ChainBuilder pause) {
        this.scenarioBuilder = this.scenarioBuilder.exec(pause);
    }

    public void feed(FeederBuilder feed) {
        this.scenarioBuilder = this.scenarioBuilder.feed(feed);
    }
}
