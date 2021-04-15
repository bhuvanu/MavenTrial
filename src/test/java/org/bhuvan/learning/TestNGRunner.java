package org.bhuvan.learning;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/login.feature",
        glue = {"org.bhuvan.learning.steps"},
        plugin = {"pretty","html:MyTestResult","summary"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true
)

public class TestNGRunner extends AbstractTestNGCucumberTests {

}
