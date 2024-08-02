package io.codeka.glue;

import io.cucumber.java.en.Given;

import static io.gatling.javaapi.core.CoreDsl.*;

/**
 * Steps definition used to inject users
 */
public class UsersInjectionStepDefinitions {

    private final CucumberGatlingScenarioBuilderHelper helper;

    public UsersInjectionStepDefinitions(CucumberGatlingScenarioBuilderHelper helper) {
        this.helper = helper;
    }

    @Given("{int} constant user(s) per second during {int} second(s)")
    public void constantUsersPerSecondDuringSeconds(Integer rate, Integer duration) {
        var injection = constantUsersPerSec(rate).during(duration.longValue());
        helper.addInjectionStep(injection);
    }

    @Given("{int} randomized constant user(s) per second during {int} second(s)")
    public void randomizedConstantUsersPerSecondDuringSeconds(Integer rate, Integer duration) {
        var injection = constantUsersPerSec(rate).during(duration.longValue()).randomized();
        helper.addInjectionStep(injection);
    }

    @Given("no user(s) for {int} seconds")
    public void noUsersFor(Long duration) {
        helper.addInjectionStep(nothingFor(duration));
    }

    @Given("{int} user(s) at once")
    public void userAtOnce(Integer users) {
        // Write code here that turns the phrase above into concrete actions
        helper.addInjectionStep(atOnceUsers(users));
    }
}
