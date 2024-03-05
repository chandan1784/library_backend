package com.studyroom.notification.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

@Configuration
public class NotificationConfiguration {

	@Value("${accessKey}")
	private String accesskey;

	@Value("${secretKey}")
	private String secretKey;

	@Value("${region}")
	private String region;

	@Bean
	public AmazonSimpleEmailService amazonSimpleEmailService() {

		BasicAWSCredentials credentials = new BasicAWSCredentials(accesskey, secretKey);

		return AmazonSimpleEmailServiceClientBuilder.standard().withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
	}
}
