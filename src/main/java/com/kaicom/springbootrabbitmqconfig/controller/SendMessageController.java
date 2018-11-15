package com.kaicom.springbootrabbitmqconfig.controller;

import com.kaicom.springbootrabbitmqconfig.bean.Message;
import com.kaicom.springbootrabbitmqconfig.produce.SendTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class SendMessageController {
    @Autowired
    private SendTopic sendTopic;
    @GetMapping("/sendMessage/{routingKey}")
    public String sendMessage(@PathVariable("routingKey") String routingKey){
        for(int i = 0 ; i < 99999999 ; i++){
            Message message = new Message();
            message.setId(i);
            message.setContent("请通知设备科的科长"+i);
            message.setTitle("紧急通知"+i);
            message.setSendTime(new Date());
            message.setSendUserName("张刷"+i);
            message.setRoutingKey(routingKey);
            sendTopic.sendMessage(message);
            /*try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        return "success";
    }
}
