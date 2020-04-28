package com.xu.miaosha.rabbitmq;

import com.xu.miaosha.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: miaosha_idea
 * @description: 消息队列发送者
 * @author: Xu Changqing
 * @create: 2020-04-26 02:06
 **/
@Service
public class MQSender {
    private static Logger log = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendMiaoshaMessage(MiaoshaMessage miaoshaMessage) {
        String msg = RedisService.beanToString(miaoshaMessage);
//        log.info("send message:" + msg);
        amqpTemplate.convertAndSend(MQConfig.Miaosha_QUEUE, msg);
    }

//    public void send(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info("send message:" + message);
//        amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);
//    }
//
//    public void sendTopic(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info(msg);
//        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, MQConfig.ROUTING_KEY1, msg + "1");
//        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, MQConfig.ROUTING_KEY2, msg + "2");
//    }
//
//    public void sendFanout(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info("send sendFanout: " + msg);
//        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "", msg + "1");
//    }
//
//    public void sendHeader(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info("send sendHeader: " + msg);
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setHeader("header1", "value1");
//        messageProperties.setHeader("header2", "value2");
//        Message message1 = new Message(msg.getBytes(),messageProperties);
//        amqpTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE, "", message1);
//    }
//
//

}
