package com.jbehave.Steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class HomeSteps extends Steps {

    private static WebDriver driver=null;

    @Given("browser is opened")
    public void openABrowser(){
    if(driver==null){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }


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
