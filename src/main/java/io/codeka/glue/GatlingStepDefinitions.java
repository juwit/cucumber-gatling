package io.codeka.glue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.gatling.javaapi.core.OpenInjectionStep;
import io.gatling.javaapi.core.ScenarioBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * This Cucumber Glue (StepDefinition) that generates a Gatling Scenario as an output.
 * This class can be extended to allow adding your own definitions.
 */
public class GatlingStepDefinitions {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatlingStepDefinitions.class.getName());

    private ScenarioBuilder scenarioBuilder;

    private List<OpenInjectionStep> injectionSteps;

    private final GatlingScenarioRegistry scenarioRegistry;

    public GatlingStepDefinitions(GatlingScenarioRegistry scenarioRegistry) {
        this.scenarioRegistry = scenarioRegistry;
    }

    public ScenarioBuilder getScenarioBuilder() {
        return scenarioBuilder;
    }

    public void addInjectionStep(OpenInjectionStep injectionStep) {
        this.injectionSteps.add(injectionStep);
    }

    /**
     * This hook re-creates a new Gatling scenario for the current Cucumber scenario, using the Cucumber scenario name.
     * @param scenario the Cucumber scenario
     */
    @Before
    public void before(Scenario scenario) {
        LOGGER.info("Reading Gatling Scenario {}", scenario.getName());
        scenarioBuilder = scenario(scenario.getName());
        injectionSteps = new ArrayList<>();
    }

    @After
    public void after(Scenario scenario) {
        LOGGER.info("Sending scenario {} to Gatling", scenario.getName());
        this.scenarioRegistry.add(scenarioBuilder.injectOpen(this.injectionSteps));
    }

    @Given("{int} constant user(s) per second during {int} second(s)")
    public void constantUsersPerSecondDuringSeconds(Integer rate, Integer duration) {
        // saving injection step to build the scenario when step definition ends
        var injection = constantUsersPerSec(rate).during(duration.longValue());
        injectionSteps.add(injection);
    }

    @Given("{int} randomized constant user(s) per second during {int} second(s)")
    public void randomizedConstantUsersPerSecondDuringSeconds(Integer rate, Integer duration) {
        // saving injection step to build the scenario when step definition ends
        var injection = constantUsersPerSec(rate).during(duration.longValue()).randomized();
        injectionSteps.add(injection);
    }

    @Given("no user(s) for {int} seconds")
    public void noUsersFor(Long duration) {
        // Write code here that turns the phrase above into concrete actions
        var atOnceInjection = nothingFor(duration);
        this.injectionSteps.add(atOnceInjection);
    }

    @Given("{int} user(s) at once")
    public void userAtOnce(Integer users) {
        // Write code here that turns the phrase above into concrete actions
        var atOnceInjection = atOnceUsers(users);
        this.injectionSteps.add(atOnceInjection);
    }

    @When("http request {string}")
    public void http_request_http_gatling_io(String domain) {
        // Write code here that turns the phrase above into concrete actions
        scenarioBuilder = scenarioBuilder.exec(
                http("Http Request").get(domain)
        );
    }

    @When("pause of {int} second(s)")
    public void pause(int seconds) {
        scenarioBuilder = scenarioBuilder.pause(seconds);
    }

    @When("pause between {int} and {int} seconds")
    public void pause(int minSeconds, int maxSeconds) {
        scenarioBuilder = scenarioBuilder.pause(minSeconds, maxSeconds);
    }



}
