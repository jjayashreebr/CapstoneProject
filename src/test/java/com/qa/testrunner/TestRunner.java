package com.qa.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;



@CucumberOptions(
        features = "src/test/java/com/qa/features/search.feature",
        glue = "com.qa.stepDefinitions"
        )
   

public class TestRunner  extends AbstractTestNGCucumberTests{
	

}
