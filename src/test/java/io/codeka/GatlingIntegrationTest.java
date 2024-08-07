package io.codeka;

import io.codeka.glue.GatlingScenarioRegistry;
import io.gatling.app.Gatling$;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GatlingIntegrationTest {

    @AfterAll
    static void afterAll() {
        // cleanup the static registry
        GatlingScenarioRegistry.getAllScenarios().clear();
    }

    public static class GatlingIntegrationTestCucumberSimulation extends CucumberSimulation {
        @Override
        public String getFeaturePath() {
            return "classpath:/features/GatlingIntegrationTest.feature";
        }
    }

    @Test
    void testGatlingRun() {
        // run gatling as if it was run from command line
        int returnCode = Gatling$.MODULE$.fromArgs(new String[]{
                "--simulation="+GatlingIntegrationTestCucumberSimulation.class.getName(),
                "--results-folder=target/"
        });
        assertEquals(0, returnCode);
    }
}
