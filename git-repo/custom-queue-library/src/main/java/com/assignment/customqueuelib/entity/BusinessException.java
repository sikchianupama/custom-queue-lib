package com.assignment.customqueuelib.entity;


import java.text.MessageFormat;

public class BusinessException extends Exception {

    private static final long serialVersionUID = 1L;
    protected String code;

    protected String message;

    protected String  responseStatus;

    public BusinessException() {
        super();
    }

    public BusinessException(String serviceStatus, Object... messages) {
        super(MessageFormat.format(serviceStatus, messages));
        this.code = serviceStatus;
        this.message =
                MessageFormat.format(serviceStatus, messages);
        this.responseStatus = serviceStatus;
    }


    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String toFormattedMsg() {
        return "Error occurred code: " + this.code + " :: message: "
                + this.message;
    }


    


}
