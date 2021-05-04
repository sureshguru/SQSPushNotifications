package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@EnableSqs
@Configuration
public class SpringCloudSQSConfig {
	
	//These credentials will expire in a week
	@Value("${cloud.aws.credentials.accesskey : AKIAZNW7YKAF2FHRYQHZ}")
	private String accessKey;
	
	//These credentials will expire in a week
	@Value("${cloud.aws.credentials.secretkey : vsmZH0V6Tg7qQk3kXwUKX8MMMpiSsZJHOBq5fheM}")
	private String secretKey;

	@Bean
	public QueueMessagingTemplate queueMessagingTemplate() {
		return new QueueMessagingTemplate(amazonSQSAsync());
	}

	@Bean
	public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(){
	    SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
	    factory.setAmazonSqs(amazonSQSAsync());
	    factory.setMaxNumberOfMessages(10);
	    factory.setAutoStartup(true);
	    return factory;
	}
	
	/**
	 * 
	 * @return
	 */
	public AmazonSQSAsync amazonSQSAsync() {

		AmazonSQSAsyncClientBuilder amazonSQSAsyncClientBuilder = AmazonSQSAsyncClientBuilder.standard();
		AmazonSQSAsync amazonSQSAsync = null;
		amazonSQSAsyncClientBuilder.withRegion(Regions.US_EAST_1);
		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
		amazonSQSAsyncClientBuilder.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials));
		amazonSQSAsync = amazonSQSAsyncClientBuilder.build();
		return amazonSQSAsync;

	}
}