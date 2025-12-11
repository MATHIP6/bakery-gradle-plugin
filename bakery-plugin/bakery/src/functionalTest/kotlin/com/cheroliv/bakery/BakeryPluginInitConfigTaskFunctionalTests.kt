package com.cheroliv.bakery

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.io.TempDir
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import java.io.File
import kotlin.test.BeforeTest

class BakeryPluginInitConfigTaskFunctionalTests {
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
        // Do site.yml exist in root project folder?
        assertThat(projectDir.resolve("site.yml"))
            .describedAs("site.yml should not exists yet")
            .doesNotExist()
    }


}