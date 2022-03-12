package com.assignment.customqueuelib.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueueMessage {
    @JsonProperty("queueHeader")
    private String queueHeader;
    @JsonProperty("queueBody")
    private String queueBody;
    private String queueName;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueHeader() {
        return queueHeader;
    }

    public void setQueueHeader(String queueHeader) {
        this.queueHeader = queueHeader;
    }

    public String getQueueBody() {
        return queueBody;
    }

    public void setQueueBody(String queueBody) {
        this.queueBody = queueBody;
    }
}
