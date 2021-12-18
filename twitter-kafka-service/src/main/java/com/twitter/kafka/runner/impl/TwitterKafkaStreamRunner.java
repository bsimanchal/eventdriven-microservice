package com.twitter.kafka.runner.impl;

import com.twitter.kafka.config.TwitterKafkaConfigData;
import com.twitter.kafka.listener.TwitterKafkaStatusListener;
import com.twitter.kafka.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;

@Component
public class TwitterKafkaStreamRunner implements StreamRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStreamRunner.class);

    private final TwitterKafkaConfigData twitterKafkaConfigData;
    private final TwitterKafkaStatusListener twitterKafkaStatusListener;

    private TwitterStream twitterStream;

    public TwitterKafkaStreamRunner(TwitterKafkaConfigData configData,
                                    TwitterKafkaStatusListener statusListener) {
        this.twitterKafkaConfigData = configData;
        this.twitterKafkaStatusListener = statusListener;
    }

    @Override
    public void start() throws TwitterException {
        twitterStream = TwitterStreamFactory.getSingleton();
        twitterStream.addListener(twitterKafkaStatusListener);
        addFilter();
    }

    @PreDestroy
    public void shutdown() {
        if(twitterStream != null) {
            LOG.info("Closing twitter stream!");
            twitterStream.shutdown();
        }
    }

    private void addFilter() {
        String[] keywords = twitterKafkaConfigData.getTwitterKeywords().toArray(new String[0]);
        FilterQuery filterQuery = new FilterQuery(keywords);
        twitterStream.filter(filterQuery);
        LOG.info("Started filtering twitter stream for the configured keywords {}...", keywords);
    }
}
