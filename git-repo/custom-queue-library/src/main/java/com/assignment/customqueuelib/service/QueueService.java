package com.assignment.customqueuelib.service;

import com.assignment.customqueuelib.core.QueueProvider;
import com.assignment.customqueuelib.core.SubscriberProvider;
import com.assignment.customqueuelib.entity.BusinessException;
import com.assignment.customqueuelib.entity.QueueMessage;
import com.assignment.customqueuelib.entity.QueueProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueService {
    @Autowired
    private QueueProvider queueProvider;
    @Autowired
    private SubscriberProvider subscriberProvider;
    public boolean addQueue(QueueProperties queueProperties){
        queueProvider.addQueue(queueProperties.getQueueName());
        queueProvider.addProperties(queueProperties);
        return true;
    }



    public void addMessageToQueue(String producerName, String queueName, QueueMessage message) throws BusinessException {
        message.setQueueName(queueName);
        queueProvider.addMessageToQueue(producerName,queueName,message);
    }

    public void addProducer(String producerName, String queueName) {
        queueProvider.addProducer(producerName,queueName);
    }

    public void addSubscriber(String subscriberName, String queueName) throws BusinessException {
        subscriberProvider.addSubscriber(subscriberName,queueName);

    }
    public void removeSubscriber(String subscriberName, String queueName) throws BusinessException {
        subscriberProvider.removeSubscriber(subscriberName,queueName);

    }
}
