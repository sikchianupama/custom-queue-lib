package com.assignment.customqueuelib.core;

import com.assignment.customqueuelib.entity.BusinessException;
import com.assignment.customqueuelib.entity.IQueueListener;
import com.assignment.customqueuelib.entity.QueueMessage;
import com.assignment.customqueuelib.entity.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SubscriberProvider {
    @Autowired
    Subscriber subscriber;
    @Autowired
    IQueueListener queueListener;

    public static final Logger LOGGER = LoggerFactory.getLogger(SubscriberProvider.class);
   Map <String, Set<String>>  subscriberMap= new HashMap<>();
   public  Set<String> getSubscriber(String queueName){
       Set<String> subscribers= subscriberMap.get(queueName);
       if(Objects.isNull(subscribers)){
           LOGGER.error("There is no subscriber to this queue");
           return new HashSet<>();

       }
       else {
           return subscribers;
       }


   }

    public void addSubscriber(String subscriberName, String queueName) {
        Set<String> subscribers = subscriberMap.computeIfAbsent(queueName, su -> new HashSet<>());
        subscribers.add(subscriberName);
        subscriber.registerOnQueueEventListener(queueListener);
    }

    public void removeSubscriber(String subscriberName, String queueName) {
        Set<String> subscribers = subscriberMap.computeIfAbsent(queueName, su -> new HashSet<>());
        subscribers.remove(subscriberName);
        subscriber.unregisterOnQueueEventListener(queueListener);
    }

    public void processMessage(String subscriberName, QueueMessage queueMessage)  {

        subscriber.processMessage(queueMessage.getQueueBody(),subscriberName);
    }
}
