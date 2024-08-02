package io.codeka.glue;

import io.cucumber.java.en.When;

import static io.gatling.javaapi.http.HttpDsl.http;

public class HttpStepDefinitions {

    private final CucumberGatlingScenarioBuilderHelper helper;

    public HttpStepDefinitions(CucumberGatlingScenarioBuilderHelper helper) {
        this.helper = helper;
    }

    @When("http request {string}")
    public void http_request_http_gatling_io(String domain) {
        // Write code here that turns the phrase above into concrete actions
        helper.addStep(
                http("Http Request").get(domain)
        );
    }
}
