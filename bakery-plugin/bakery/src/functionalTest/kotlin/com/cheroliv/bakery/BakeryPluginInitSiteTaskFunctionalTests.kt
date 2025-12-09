package com.cheroliv.bakery

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.io.TempDir
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import java.io.File
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.text.Charsets.UTF_8

class BakeryPluginInitSiteTaskFunctionalTests {

    companion object {
        private val log: Logger by lazy { getLogger(BakeryPluginInitSiteTaskFunctionalTests::class.java) }

        fun info(message: String) {
            message
                .apply(log::info)
                .run(::println)
        }
    }

    @field:TempDir
    lateinit var projectDir: File

    @BeforeTest
    fun prepare() {
        "${BakeryPluginInitSiteTaskFunctionalTests::class.java.simpleName}.projectDir exists, path: $projectDir"
            .run(::info)
        info("Prepare temporary directory to host gradle build.")
        createSettingsFile(projectDir)
    }

    private fun createSettingsFile(projectDir: File) {
        projectDir.resolve("settings.gradle.kts").run {
            assertThat(exists())
                .describedAs("settings.gradle.kts should not exists yet.")
                .isFalse
            assertThat(createNewFile())
                .describedAs("setting.gradle.kts should be created.")
                .isTrue
            writeText(
                """
            @file:Suppress("UnstableApiUsage")

            pluginManagement {
                repositories {
                    mavenLocal()
                    gradlePluginPortal()
                    mavenCentral()
                    google()
                }
            }

            dependencyResolutionManagement {
                repositories {
                    mavenLocal()
                    mavenCentral()
                    google()
                }
            }

            rootProject.name = "bakery-test"
        """
            )
            assertThat(exists())
                .describedAs("settings.gradle.kts should now exists.")
                .isTrue

            assertThat(readText(UTF_8))
                .describedAs("settings.gradle.kts should contains at least 'bakery-test'")
                .contains("bakery-test")

        }
    }

    @Suppress("DANGEROUS_CHARACTERS", "FunctionName")
    @Test
    fun `Template folder does not exist`() {
        info("initSiteTest")
        info("Delete temporary directory if exists.")
        info("Project temporary path : ${projectDir.path}")
        if (projectDir.resolve("src/jbake").exists()) {
            projectDir.resolve("src/jbake").deleteRecursively()
        }
        assertThat(projectDir.resolve("src/jbake").exists())
            .describedAs("src/jbake should not exists anymore in temporary project folder : ${projectDir.path}")
            .isFalse
        info("Do template folder exist in default path : src/jbake?")
        // Est ce que le dossier src/jbake existe?
    }
}