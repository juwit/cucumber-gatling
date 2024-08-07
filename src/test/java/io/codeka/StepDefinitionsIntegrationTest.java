package io.codeka;

import io.codeka.glue.GatlingScenarioRegistry;
import io.gatling.core.Predef$;
import io.gatling.core.config.GatlingConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This test validates that all Cucumber step definitions are correct.
 */
public class StepDefinitionsIntegrationTest {

    @BeforeAll
    static void beforeAll() {
        // setup gatling statics to allow creation of gatling objects outside of gatling context
        // this prevents this exception :
        // java.lang.IllegalStateException: Simulations can't be instantiated directly but only by Gatling
        Predef$.MODULE$._configuration_$eq(GatlingConfiguration.load());
    }

    @AfterAll
    static void afterAll() {
        // cleanup the static registry
        GatlingScenarioRegistry.getAllScenarios().clear();
    }

    @Test
    void testStepDefinitions() {
        CucumberRunner cucumberRunner = new CucumberRunner();
        var result = cucumberRunner.runCucumberFeature("classpath:/features/all.feature");

        assertTrue(result);
    }
}
