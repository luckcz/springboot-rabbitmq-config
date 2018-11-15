package com.kaicom.springbootrabbitmqconfig.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RabbitmqConfig {
    /*@Resource
    private RabbitTemplate rabbitTemplate ;*/

    @Bean
    public Queue messageQueue1(){
        return new Queue("topic.message1");
    }
    @Bean
    public Queue messageQueue2(){
        return new Queue("topic.message2");
    }
    @Bean
    public Queue messageQueue3(){
        return new Queue("topic.message3");
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 将队列userQueue与exchange绑定，binding_key为topic.user,就是完全匹配
     */
   /* @Bean
    Binding bindingExchangeUser(Queue userQueue, TopicExchange exchange){
        return BindingBuilder.bind(userQueue).to(exchange).with("topic.user");
    }*/

    /**
     * 将队列messageQueue1与exchange绑定，binding_key为topic.#,就是模糊匹配
     */
    @Bean
    Binding bindingExchangeMessage1(Queue messageQueue1, TopicExchange exchange){
        return BindingBuilder.bind(messageQueue1).to(exchange).with("hello.abc");
    }

    /**
     * 将队列messageQueue2与exchange绑定，binding_key为topic.#,就是模糊匹配
     */
    @Bean
    Binding bindingExchangeMessage2(Queue messageQueue2, TopicExchange exchange){
        return BindingBuilder.bind(messageQueue2).to(exchange).with("hello.*");
    }

    /**
     * 将队列messageQueue3与exchange绑定，binding_key为topic.#,就是模糊匹配
     */
    @Bean
    Binding bindingExchangeMessage3(Queue messageQueue3, TopicExchange exchange){
        return BindingBuilder.bind(messageQueue3).to(exchange).with("hello.#");
    }

    /** ======================== 定制一些处理策略 =============================*/

    /**
     * 定制化amqp模版
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
     * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
     * 以下代码需要自己手动配置rabbitmq的连接工厂，不能使用默认的rabbitmq
     */
    /*
    public RabbitTemplate rabbitTemplate() {
        System.err.println("进入回执---------------");
        Logger logger = LoggerFactory.getLogger(getClass());
        // 消息发送失败返回到队列中, 配置文件需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        // 消息返回, 配置文件需要配置 publisher-returns: true
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            logger.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        });

        // 消息确认, 配置文件需要配置 publisher-confirms: true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                System.err.println("进入回执成功---------------");
                logger.info("消息发送到exchange成功,id: {}", correlationData.getId());
            } else {
                logger.info("消息发送到exchange失败,原因: {}", cause);
            }
        });
        return rabbitTemplate;
    }*/
}
