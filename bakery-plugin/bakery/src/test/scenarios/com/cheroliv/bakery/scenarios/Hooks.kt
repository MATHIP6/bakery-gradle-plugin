package com.cheroliv.bakery.scenarios


import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import kotlinx.coroutines.runBlocking

class Hooks(private val world: TestWorld) {

    @Before
    fun setUp(scenario: Scenario) {
        world.log.info("Starting scenario: ${scenario.name}")
    }

    @After
    fun tearDown(scenario: Scenario) = runBlocking {
        world.log.info("Finishing scenario: ${scenario.name}")

        // Attendre la fin de toutes les opérations asynchrones
        world.awaitAll()

        // Nettoyer les ressources
        world.cleanup()
    }
}
