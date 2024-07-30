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

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

/**
 * This Cucumber Glue (StepDefinition) generates a Gatling Scenario as an output !
 */
public class GatlingStepDefinitions {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatlingStepDefinitions.class.getName());

    private ScenarioBuilder scenarioBuilder;

    private OpenInjectionStep injectionStep;

    private GatlingScenarioRegistry scenarioRegistry;

    public GatlingStepDefinitions(GatlingScenarioRegistry scenarioRegistry) {
        this.scenarioRegistry = scenarioRegistry;
    }

    @Before
    public void before(Scenario scenario) {
        LOGGER.info("Reading Gatling Scenario {}", scenario.getName());
        scenarioBuilder = scenario(scenario.getName());
    }

    @After
    public void after(Scenario scenario) {
        LOGGER.info("Sending scenario {} to Gatling", scenario.getName());
        this.scenarioRegistry.add(scenarioBuilder.injectOpen(this.injectionStep));
    }

    @Given("{int} constant users per second during {int} seconds")
    public void constant_users_per_second_during_seconds(Integer rate, Integer duration) {
        // saving injection step to build the scenario when step definition ends
        this.injectionStep = constantUsersPerSec(rate).during(duration.longValue());
    }

    @When("http request {string}")
    public void http_request_http_gatling_io(String domain) {
        // Write code here that turns the phrase above into concrete actions
        scenarioBuilder = scenarioBuilder.exec(
                http("Http Request").get(domain)
        );
    }

    @When("pause of {int} seconds")
    public void pause(int seconds) {
        scenarioBuilder = scenarioBuilder.pause(seconds);
    }

    @When("pause between {int} and {int} seconds")
    public void pause(int minSeconds, int maxSeconds) {
        scenarioBuilder = scenarioBuilder.pause(minSeconds, maxSeconds);
    }

    @Given("{int} user")
    public void user(Integer users) {
        // Write code here that turns the phrase above into concrete actions
        this.injectionStep = atOnceUsers(users);
    }



}
