package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.*", })
public class NotificationConsumerApplication {


	
	public static void main(String[] args) {
		System.out.print("test");
		//AWSUtil awsUtil = new AWSUtil();
		//SQSUtil sqsUtil = new SQSUtil(awsUtil);
		//sqsUtil.receiveMessage();
		SpringApplication.run(NotificationConsumerApplication.class, args);
		
	}

}
