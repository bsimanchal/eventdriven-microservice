package com.twitter.kafka.runner;

import twitter4j.TwitterException;

public interface StreamRunner {
    void start() throws TwitterException;
}
