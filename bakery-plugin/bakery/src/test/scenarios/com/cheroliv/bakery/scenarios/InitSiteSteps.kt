package com.cheroliv.bakery.scenarios

import io.cucumber.java.en.And
import org.assertj.core.api.Assertions.assertThat

class InitSiteSteps(private val world: TestWorld) {

    @And("I add a buildScript file with {string} as the config path in the dsl")
    fun checkBuildScript(configfile: String) {
        assertThat(
            world.projectDir!!
                .resolve("build.gradle.kts")
                .readText()
        ).describedAs("Should contains plugins block and bakery dsl.")
            .contains(
                "plugins { id(\"com.cheroliv.bakery\") }",
                "bakery { configPath = file(\"site.yml\").absolutePath }"
            )
    }
//
//    @When("I am executing the task {string}")
//    fun jExecuteLaTache(taskName: String) = runBlocking {
//        world.executeGradle(taskName)
//    }
//
//    @When("I am waiting for all asynchronous operations to complete")
//    fun jAttendsFinOperations() = runBlocking {
//        world.awaitAll()
//    }
//
//    @Then("the build should succeed")
//    fun leBuildDevraitReussir() {
//        assertThat(world.buildResult).isNotNull
//    }
}
