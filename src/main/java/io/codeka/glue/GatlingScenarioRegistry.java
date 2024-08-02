package io.codeka.glue;

import io.gatling.javaapi.core.PopulationBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds the Gatling Scenarios generated by the Cucumber execution.
 * There's no clean way to be able to extract data from the Cucumber Step definition (besides using a dependency injection tool), so it's a plain old-fashioned singleton for now.
 */
public class GatlingScenarioRegistry {

    private static final List<PopulationBuilder> populationBuilders = new ArrayList<>();

    public static List<PopulationBuilder> getAllScenarios() {
        return populationBuilders;
    }

    public void add(PopulationBuilder populationBuilder) {
        populationBuilders.add(populationBuilder);
    }
}
