package com.assignment.customqueuelib.entity;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QueueDetails extends Observable {
    public String queueName;

    private List<QueueMessage> qData;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public List<QueueMessage> getqData() {
        return qData;
    }

    public void setqData(List<QueueMessage> qData) {
        this.qData = qData;
    }

    public void addQueueMessage(QueueMessage message) {
        if(Objects.isNull(qData))
            qData=new ArrayList<>();
        qData.add(message);
        setChanged();
        notifyObservers(message);
    }

    public void registerObservers(List<Observer> subscribers, QueueDetails queueDetails) {
        subscribers.forEach(observer -> {
            queueDetails.addObserver(observer);
        });

    }
}
