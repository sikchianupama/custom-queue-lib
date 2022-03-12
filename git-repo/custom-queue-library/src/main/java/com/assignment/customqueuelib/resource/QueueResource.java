package com.assignment.customqueuelib.resource;

import com.assignment.customqueuelib.entity.BusinessException;
import com.assignment.customqueuelib.entity.QueueMessage;
import com.assignment.customqueuelib.entity.QueueProperties;
import com.assignment.customqueuelib.entity.Subscriber;
import com.assignment.customqueuelib.entity.to.MessageTO;
import com.assignment.customqueuelib.entity.to.ProducerTO;
import com.assignment.customqueuelib.entity.to.SubscriberTO;
import com.assignment.customqueuelib.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping({ "/queue-lib" })
@Controller
@RestController
public class QueueResource {
    @Autowired
    private QueueService queueService;

    @RequestMapping(value = "/addQueue", method = RequestMethod.POST)
    @ResponseBody
    public String addQueue(QueueProperties queueProperties) {
        queueService.addQueue(queueProperties);
        return "queue added";
    }

    @RequestMapping(value = "/deleteQueue", method = RequestMethod.POST)
    @ResponseBody
    public String deleteQueue(String queueName) {
        return "queue deleted";
    }

    //    @RequestMapping(value="/getQueue",method = RequestMethod.POST)
    //    @ResponseBody
    //    public ResponseEntity<List<QueueMessage>> getAllMessageFromQueue(String queueName) {
    //        List<QueueMessage> messageList=queueService.getAllMessages(queueName);
    //        return ResponseEntity.ok(messageList);
    //    }



    @RequestMapping(value = "/addProducer", method = RequestMethod.POST,consumes={"application/JSON"},produces = { "application/JSON" })
    @ResponseBody
    public ResponseEntity<String>  addProducer(@RequestBody ProducerTO producerTO) {
         queueService.addProducer(producerTO.getProducerName(),producerTO.getQueueName());
        return ResponseEntity.ok("Producer Mapped with Queue successfully ");
    }
/*
Request json:{
   "producerName":"p1",
   "queueName":"Q1",
   "queueMessage":{
       "queueHeader":"",
       "queueBody":""

   }
}

 */
    @RequestMapping(value = "/addMessageToQueue", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addMessageToQueue(@RequestBody MessageTO messageTO) {
        try {
            queueService.addMessageToQueue(messageTO.getProducerName(), messageTO.getQueueName(), messageTO.getMessage());
            return ResponseEntity.ok("Message added to queue successfully ");
        } catch (BusinessException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @RequestMapping(value = "/addSubscriber", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addSubscribers(@RequestBody SubscriberTO subscriberTO) {
        try {
            queueService.addSubscriber(subscriberTO.getSubscriberName(), subscriberTO.getQueueName());
            return ResponseEntity.ok("Subscriber added successfully ");
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    @RequestMapping(value = "/removeSubscriber", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> remove(@RequestBody SubscriberTO subscriberTO) {
        try {
            queueService.addSubscriber(subscriberTO.getSubscriberName(), subscriberTO.getQueueName());
            return ResponseEntity.ok("Subscriber removed successfully ");
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
