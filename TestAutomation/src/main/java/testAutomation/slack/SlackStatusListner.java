package testAutomation.slack;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class SlackStatusListner extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        String description = result.getMethod().getDescription();
        long timeDuration = result.getEndMillis() - result.getStartMillis();
        if(description == null || description.isEmpty()){
            description = result.getName();
        }
        SlackIntegrator s = new SlackIntegrator();
        s.publishResult(description, "Failed", timeDuration);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String description = result.getMethod().getDescription();
        long timeDuration = result.getEndMillis() - result.getStartMillis();
        if(description == null || description.isEmpty()){
            description = result.getName();
        }
        SlackIntegrator s = new SlackIntegrator();
        s.publishResult(description, "Passed", timeDuration);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // do what you want to do
    }
}
