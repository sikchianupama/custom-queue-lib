package com.assignment.customqueuelib.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QueueListener implements IQueueListener {
    public static final Logger LOGGER = LoggerFactory.getLogger(QueueListener.class);
    @Override
    public void process(String message, String queueName)  {
        LOGGER.info("Subscriber of {} message {}",queueName,message);

    }
}
