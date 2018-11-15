package com.kaicom.springbootrabbitmqconfig.consumer;

import com.kaicom.springbootrabbitmqconfig.bean.Message;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RabbitListener(queues = "topic.message3")
public class ReceivedTopic3 {
    @RabbitHandler
    public  void receivMessage3(@Payload Message message, @Headers Map<String,Object> headers, Channel channel) throws Exception {
        //System.err.println("-------consumer three start consume----hello.#---");
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        //System.err.println("--------DELIVERY_TAG--------"+deliveryTag);
        System.err.println("hello.#  收到消息-------->"+message.getSendUserName());
        //手动确认消息
        channel.basicAck(deliveryTag,false);
    }
}
