package io.codeka;

import io.gatling.app.Gatling$;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GatlingIntegrationTest {

    @Test
    void testGatlingRun() {
        int returnCode = Gatling$.MODULE$.fromArgs(new String[]{
                "--simulation=io.codeka.CucumberSimulation",
                "--results-folder=target/"
        });
        assertEquals(0, returnCode);
    }
}
