package com.assignment.customqueuelib.entity;

import com.assignment.customqueuelib.core.SubscriberProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

@Component
public class QueueSubscriber implements Observer {
    @Autowired
    SubscriberProvider subscriberProvider;
    @Override
    public void update(Observable o, Object arg) {
        QueueMessage queueMessage=(QueueMessage)arg;
        Set<String> subscribers=subscriberProvider.getSubscriber(queueMessage.getQueueName());
        subscribers.forEach(subscriber -> {
            subscriberProvider.processMessage(subscriber,queueMessage);
        });

    }

}
