package com.assignment.customqueuelib.core;

import com.assignment.customqueuelib.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class QueueProvider  {
    @Autowired
    private  QueueDetails queueDetails;
    @Autowired
    private QueueSubscriber queueSubscriber;

    @PostConstruct
    public void init() throws Exception {
        queueDetails.registerObservers(Arrays.asList(queueSubscriber),queueDetails);
    }
    public static final Logger LOGGER = LoggerFactory.getLogger(QueueProvider.class);
    private  ConcurrentHashMap<String, QueueDetails> mqMessages=new ConcurrentHashMap<>();
    private  ConcurrentHashMap<String,QueueProperties> mqProperties=new ConcurrentHashMap<>();
    private  ConcurrentHashMap<String,String> producerQueueName=new ConcurrentHashMap<>();
    private List<Observer> subscribers = new ArrayList<>();
    private QueueProvider(){}

    public  void addQueue(String queueName){
        queueDetails.setQueueName(queueName);
        this.mqMessages.put(queueName,queueDetails);
    }

    public  void addProducer(String producerName, String queueName) {
        producerQueueName.put(queueName,producerName);
    }

    public  void removeQueue(String name){
        mqMessages.remove(name);
    }


    public  QueueDetails getMqMessages(String name) {
            return mqMessages.get(name);


    }
    public  void addMessageToQueue(String producer, String queueName, QueueMessage queueMessage) throws BusinessException {
        if(producerQueueName.get(queueName).equalsIgnoreCase(producer)){
            LOGGER.debug("Valid producer {} to the queueName {}",producer,queueName);
            QueueDetails queueDetailsNew=mqMessages.get(queueName);
            //adding to a new queue if the queue is not present
            if(Objects.isNull(queueDetailsNew)){
                queueDetails.setQueueName(queueName);
                mqMessages.put(queueName, queueDetails);
                queueDetails.addQueueMessage(queueMessage);
            }
            else {
                queueDetails.addQueueMessage(queueMessage);
            }
        }
        else {
            LOGGER.error("The producer producing to queue {} is invalid",queueName);
            throw new BusinessException("INVALID_PRODUCER","The producer producing to queue"+queueName+"is invalid");
        }



    }

    public  void addProperties(QueueProperties queueProperties){
        mqProperties.put(queueProperties.getQueueName(),queueProperties);
    }
    public void attach(Observer o){
    subscribers.add(o);
    }
    public void detach(Observer o){
        subscribers.remove(o);
    }


}
