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
@RabbitListener(queues = "topic.message1")
public class ReceivedTopic1 {
    @RabbitHandler
    public  void receivMessage1(@Payload Message message, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        //System.err.println("-------consumer one start consume----hello.abc---");
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        //System.err.println("--------DELIVERY_TAG--------"+deliveryTag);
        System.err.println(" hello.abc 收到消息-------->"+message.getSendUserName());
        //手动确认消息
        channel.basicAck(deliveryTag,false);
    }
}
