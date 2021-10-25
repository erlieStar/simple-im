package com.javashitang.component.amqp;

import com.javashitang.domain.ChatMessage;
import com.javashitang.service.inf.ChatMessageService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 判断消息是否到达exchange
 */
@Component
public class AmqpConfirmListener implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private ChatMessageService chatMessageService;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData.getId();
        ChatMessage message = MqTemplateUtil.dequeueUnAckMsg(id);
        if (message == null) {
            return;
        }
        chatMessageService.handleSendCallback(message, ack, cause);
    }
}
