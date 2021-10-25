package com.javashitang.utils;

import com.javashitang.domain.ChatMessage;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.concurrent.ConcurrentHashMap;

public class MqTemplateUtil {

    private static ConcurrentHashMap<String, ChatMessage> unAckMsgQueue = new ConcurrentHashMap<>();

    /**
     * 发送消息，并将消息持久化
     */
    public static void convertAndSend(RabbitTemplate template, ChatMessage chatMessage) {
        String routingKey = null;
        CorrelationData data = new CorrelationData();
        data.setId(chatMessage.getMsgKey());
        template.convertAndSend("", routingKey, chatMessage, msg -> {
            MessageProperties properties = msg.getMessageProperties();
            properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return msg;
        },data);
    }

    /**
     * 所有的消息发送前都会发送到unAckMsgQueue
     */
    public static ChatMessage enqueueUnAckMsg(String msgKey, ChatMessage chatMessage) {
        return unAckMsgQueue.put(msgKey, chatMessage);
    }

    public static ChatMessage dequeueUnAckMsg(String msgKey) {
        return unAckMsgQueue.remove(msgKey);
    }
}
