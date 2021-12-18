package com.twitter.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "twitter-kafka-service")
public class TwitterKafkaConfigData {

    private List<String> twitterKeywords;
    private String welcomeMessage;

}
