package io.codeka;

import io.cucumber.core.feature.FeatureWithLines;
import io.cucumber.core.feature.GluePath;
import io.cucumber.core.options.RuntimeOptions;
import io.cucumber.core.options.RuntimeOptionsBuilder;
import io.cucumber.core.runtime.Runtime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test validates that all Cucumber step definitions are correct.
 *
 */
public class StepDefinitionsIntegrationTest {

    @Test
    void testStepDefinitions(){
        RuntimeOptions runtimeOptions = new RuntimeOptionsBuilder()
                .addGlue(GluePath.parse("io.codeka.glue"))
                .addDefaultSummaryPrinterIfNotDisabled()
                .addFeature(FeatureWithLines.parse("classpath:/features/all.feature"))
                .build();

        Runtime cucumberRuntime = Runtime.builder()
                .withRuntimeOptions(runtimeOptions)
                .build();
        cucumberRuntime.run();

        assertEquals(0, cucumberRuntime.exitStatus());
    }
}
