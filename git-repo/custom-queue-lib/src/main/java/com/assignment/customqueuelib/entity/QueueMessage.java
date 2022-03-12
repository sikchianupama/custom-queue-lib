package entity;

public class QueueMessage {
    private String queueHeader;
    private String queueBody;

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
