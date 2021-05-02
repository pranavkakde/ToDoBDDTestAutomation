package com.bddSetup;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/feature/Todo.feature",
        glue = {"com.bddSetup"},
        plugin = {"pretty","html:report/testResult.html"}
)
public class BDDSetup { 
}