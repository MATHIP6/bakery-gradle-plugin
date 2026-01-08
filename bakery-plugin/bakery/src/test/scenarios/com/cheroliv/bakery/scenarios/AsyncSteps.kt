package com.cheroliv.bakery.scenarios

import io.cucumber.java.en.*
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat

class AsyncSteps(private val world: TestWorld) {

    @Given("un nouveau projet Gradle")
    fun unNouveauProjetGradle() {
        world.createGradleProject()
        assertThat(world.projectDir).exists()
    }

    @When("j'exécute la tâche {string}")
    fun jExecuteLaTache(taskName: String) = runBlocking {
        world.executeGradle(taskName)
    }

    @When("je lance la tâche {string} en asynchrone")
    fun jeLanceTacheAsync(taskName: String) {
        @Suppress("DeferredResultUnused")
        world.executeGradleAsync(taskName)
    }

    @When("j'attends la fin de toutes les opérations asynchrones")
    fun jAttendsFinOperations() = runBlocking {
        world.awaitAll()
    }

    @Then("le build devrait réussir")
    fun leBuildDevraitReussir() {
        assertThat(world.buildResult).isNotNull
    }
}
