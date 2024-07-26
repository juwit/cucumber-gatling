package io.codeka.glue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.gatling.javaapi.core.ScenarioBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.gatling.javaapi.core.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * This Cucumber Glue (StepDefinition) generates a Gatling Scenario as an output !
 */
public class GatlingStepDefinitions {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatlingStepDefinitions.class.getName());

    private ScenarioBuilder scenarioBuilder;

    private OpenInjectionStep injectionStep;

    private GatlingScenarioRegistry scenarioRegistry;

    public GatlingStepDefinitions() {
        this.scenarioRegistry = GatlingScenarioRegistry.getInstance();
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

    @Given("{int} user")
    public void user(Integer users) {
        // Write code here that turns the phrase above into concrete actions
        this.injectionStep = atOnceUsers(users);
    }



}
