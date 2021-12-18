package com.twitter.kafka;

import com.twitter.kafka.config.TwitterKafkaConfigData;
import com.twitter.kafka.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class TwitterKafkaSpringBootApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaSpringBootApplication.class);
    private final TwitterKafkaConfigData twitterKafkaConfigData;
    private final StreamRunner streamRunner;

    public TwitterKafkaSpringBootApplication(TwitterKafkaConfigData configData,
                                             StreamRunner runner) {
        this.twitterKafkaConfigData = configData;
        this.streamRunner= runner;
    }


    public static void main(String[] args) {
        SpringApplication.run(TwitterKafkaSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Application started...");
        LOG.info(Arrays.toString(twitterKafkaConfigData.getTwitterKeywords().toArray(new String[]{})));
        LOG.info(twitterKafkaConfigData.getWelcomeMessage());
        streamRunner.start();
    }
}
