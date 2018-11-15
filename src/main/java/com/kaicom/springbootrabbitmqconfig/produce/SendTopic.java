package com.kaicom.springbootrabbitmqconfig.produce;

import com.kaicom.springbootrabbitmqconfig.bean.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SendTopic implements RabbitTemplate.ConfirmCallback{
    @Autowired
    private RabbitTemplate rabbitTemplate ;

    public void sendMessage(Message message){
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(
                "topicExchange",
                message.getRoutingKey(),
                message,
                correlationData
        );
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.err.println("消息id:" + correlationData.getId());
        if (ack) {
            System.err.println("消息发送确认成功");
        } else {
            System.err.println("消息发送确认失败:" + cause);
        }
    }
}
