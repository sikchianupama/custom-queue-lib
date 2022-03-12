package com.assignment.customqueuelib.entity;

public interface IQueueListener {
     void process(String message, String queueName) ;


}
