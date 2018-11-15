package com.kaicom.springbootrabbitmqconfig.bean;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{

    private static final long serialVersionUID = 1131804037986787860L;
    //private static final long serialVersionUID = 6243643112843633020L;
    private Integer id ;
    private String title ;
    private String content ;
    private Date sendTime ;
    private String sendUserName ;
    private String routingKey ;
    public String getRoutingKey() {
        return routingKey;
    }
    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", sendTime=" + sendTime.toLocaleString() +
                ", sendUserName='" + sendUserName + '\'' +
                '}';
    }
}
