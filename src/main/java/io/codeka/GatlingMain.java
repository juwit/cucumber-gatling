package io.codeka;

import io.gatling.app.Gatling;

/**
 * This class is for test purpose only, as Gatling should be run using the maven task.
 */
public class GatlingMain {
    public static void main(String[] args) {
        Gatling.main(new String[]{"--simulation=io.codeka.CucumberSimulation"});
    }
}
