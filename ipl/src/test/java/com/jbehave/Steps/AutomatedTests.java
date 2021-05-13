package com.jbehave.Steps;

//1
//@RunWith(SpringJUnit4ClassRunner.class)
//@ActiveProfiles(profiles="test")
//@SpringApplicationConfiguration(Application.class)
//@WebIntegrationTest("server.port:8082")

public class AutomatedTests  {/*

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
    */
}
