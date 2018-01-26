package testAutomation.slack;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.model.Attachment;
import com.github.seratch.jslack.api.model.Field;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import testAutomation.testdata.TestConfig;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SlackIntegrator {

    void publishResult(String testDescription, String result, long timeDuration){

        if(TestConfig.ENABLE_SLACK == false)
            return;
        float timeInSeconds = timeDuration/1000;
        String color = "danger";
        if(result.equalsIgnoreCase("passed")){
            color = "good";
        }else {
            color = "danger";
        }
        String url = TestConfig.SLACK_URL;
        List<Field> fields = new ArrayList<>();
        Field f = Field.builder()
                .title("Time Taken: " + String.valueOf(timeInSeconds))
                //.value(String.valueOf(timeInSeconds))
                .valueShortEnough(false)
                .build();
        fields.add(f);
        f = Field.builder()
        .title("Result: " + result)
        //.value(result)
        .valueShortEnough(false)
        .build();
        fields.add(f);
        Attachment attachment = Attachment.builder()
                .color(color)
                .title(testDescription)
                .fields(fields)
                .build();
        List<Attachment> myAttachments = new LinkedList<>();
        myAttachments.add(attachment);
        Payload payload = Payload.builder()
                .attachments(myAttachments)
                .build();
        Slack slack = Slack.getInstance();
        try {
            WebhookResponse response = slack.send(url, payload);
        }catch (Exception e){}
    }

}
