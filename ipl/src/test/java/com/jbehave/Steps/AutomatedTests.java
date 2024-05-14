package com.jbehave.Steps;

import com.backend.Application;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AutomatedTests extends JUnitStories {

    public static WebDriver driver;


    @LocalServerPort
    private int port = 8080;

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(codeLocationFromClass(this.getClass()))
                        .withFormats(Format.CONSOLE));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new HomeSteps());
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList("stories/home.story");
    }

    @Before
    public void setUp() {
        System.out.println("Into the setup method of AccountStep...");
        if(driver==null){
            //System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            //TODO:124 issue
            WebDriverManager.chromedriver().browserVersion("124").setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }

    }

    @After
    public void cleanUp() {
        System.out.println("Into the cleanUp method of AccountStep...");
        if (null != driver) {
            driver.close();
            driver.quit();
        }
    }


}
