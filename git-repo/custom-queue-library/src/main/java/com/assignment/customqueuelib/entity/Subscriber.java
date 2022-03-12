package com.assignment.customqueuelib.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Subscriber  {
    private IQueueListener mListener;
    public static final Logger LOGGER = LoggerFactory.getLogger(Subscriber.class);

    public void registerOnQueueEventListener(IQueueListener queueListener)
    {
        this.mListener = queueListener;
    }
    public void unregisterOnQueueEventListener(IQueueListener queueListener)
    {
        this.mListener = null;
    }



    public void processMessage(String message,String queueName) {

       LOGGER.info("Subscriber {} and message {}",queueName,message );

        if (this.mListener != null) {
            mListener.process(message,queueName);
        }
    }



}
