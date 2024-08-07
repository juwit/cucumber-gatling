package io.codeka.glue;

import com.redis.S;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.gatling.javaapi.http.HttpDsl.http;

public class HttpStepDefinitions {

    private final CucumberGatlingScenarioBuilderHelper helper;

    public HttpStepDefinitions(CucumberGatlingScenarioBuilderHelper helper) {
        this.helper = helper;
    }

    public record HTTPRequestParameters(Map<String, String> headers, Map<String, Object> queryParams) {}

    @DataTableType
    public HTTPRequestParameters httpParameters(DataTable parameters) {
        Map<String, String> headers = new HashMap<>();
        Map<String, Object> queryParams = new HashMap<>();

        parameters.cells().stream().skip(1).forEach(parameter -> {
            switch (parameter.getFirst()) {
                case "header" -> headers.put(parameter.get(1), parameter.get(2));
                case "query" -> queryParams.put(parameter.get(1), parameter.get(2));
                default -> throw new IllegalStateException("Unexpected value: " + parameter.getFirst());
            }
        });

        return new HTTPRequestParameters(headers, queryParams);
    }

    @When("http request {string}")
    public void http_request_http_gatling_io(String domain) {
        // Write code here that turns the phrase above into concrete actions
        helper.addStep(
                http("Http Request").get(domain)
        );
    }

    @When("http request to url {string} with parameters")
    public void http_request_http_gatling_io(String url, HTTPRequestParameters httpRequestParameters) {
        // Write code here that turns the phrase above into concrete actions
        helper.addStep(
                http("Http Request")
                        .get(url)
                        .headers(httpRequestParameters.headers())
                        .queryParamMap(httpRequestParameters.queryParams())
        );
    }
}
