package com.dailycodebuffer.sns;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan(basePackages = {"com/dailycodebuffer/sns/controller"})
public class SNSController {

    @Autowired
    private AmazonSNSClient amazonSNSClient;

    private String TOPIC_ARN = "arn:aws:sns:us-east-1:037027202319:my-sns-topic";

    @GetMapping("/subscribe")
    public String testMethod(){
        return "Testing Sucessful!!!";
    }

    @GetMapping("/subscribe/{email}")
    public String subscribeToSNSTopic(@PathVariable("email") String email){
        SubscribeRequest subscribeRequest = new SubscribeRequest(
                TOPIC_ARN, "email",email
        );

        amazonSNSClient.subscribe(subscribeRequest);
        return "Check Your Email";

    }

    @GetMapping("/publish/{msg}")
    public String publishToTopic(@PathVariable("msg") String msg){
        PublishRequest publishRequest = new PublishRequest(
                TOPIC_ARN,msg,"SNS SpringBoot"
        );
        amazonSNSClient.publish(publishRequest);
        return "Message Published";

    }
}
