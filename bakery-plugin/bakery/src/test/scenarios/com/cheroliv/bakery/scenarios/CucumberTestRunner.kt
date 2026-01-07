package com.cheroliv.bakery.scenarios

import io.cucumber.junit.platform.engine.Constants.*
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.Suite

/**
 * Test runner pour Cucumber
 * À placer dans src/test/scenarios/com/cheroliv/bakery/scenarios/
 */
@Suite
@IncludeEngines("cucumber")
// CORRECTION: Supprimer @SelectClasspathResource et utiliser FEATURES_PROPERTY_NAME à la place
@ConfigurationParameter(
    key = FEATURES_PROPERTY_NAME,
    value = "classpath:ma_nouvelle_fonctionnalite.feature"
)
@ConfigurationParameter(
    key = GLUE_PROPERTY_NAME,
    value = "com.cheroliv.bakery.scenarios"
)
@ConfigurationParameter(
    key = PLUGIN_PROPERTY_NAME,
    value = "pretty, html:build/reports/cucumber.html, json:build/reports/cucumber.json"
)
@ConfigurationParameter(
    key = FILTER_TAGS_PROPERTY_NAME,
    value = "not @wip"
)
@org.junit.platform.commons.annotation.Testable
class CucumberTestRunner