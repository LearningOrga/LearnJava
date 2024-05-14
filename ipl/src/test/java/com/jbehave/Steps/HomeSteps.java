package com.jbehave.Steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class HomeSteps extends Steps {

    private WebDriver driver;

    @Given("browser is opened")
    public void openABrowser(){
        this.driver=AutomatedTests.driver;
    }

    @When("Applicaion $ipl is launched")
    public void openApp(String ipl){
         driver.get(ipl);
    }

    @Then("$title page is displayed")
    public void checkHome(String title)    {
        Assert.assertEquals(title,driver.getTitle());
    }
}
