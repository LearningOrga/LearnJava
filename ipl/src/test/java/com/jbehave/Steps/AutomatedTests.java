package com.jbehave.Steps;

import com.backend.config.Application;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
//1
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles="test")
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest("server.port:8082")
public class AutomatedTests extends JUnitStories {

    @BeforeClass
    public static void setSystemProperty() {
        Properties properties = System.getProperties();
        properties.setProperty("spring.profiles.active", "test");
    }

    public AutomatedTests(){
        super();
        this.configuredEmbedder().candidateSteps().add(new HomeSteps());
    }

    @Override
    public Configuration configuration(){
        return new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(getClass().getClassLoader())).useStoryReporterBuilder(new StoryReporterBuilder().withFormats(Format.CONSOLE,Format.STATS,Format.HTML));
    }

    @Override
    public List<CandidateSteps> candidateSteps(){
        return new InstanceStepsFactory(configuration(),this).createCandidateSteps();
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList("stories/home.story");
    }
}
