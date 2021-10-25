package com.javashitang.component.amqp;

import com.javashitang.domain.ChatMessage;
import com.javashitang.service.inf.ChatMessageService;
import com.javashitang.tool.JsonConvert;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 注意：需要在发送消息时设置mandatory为true
 * 消息不能被正确路由到queue
 */
@Component
public class AmqpReturnListener implements RabbitTemplate.ReturnCallback {

    @Autowired
    ChatMessageService chatMessageService;

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        ChatMessage chatMessage = JsonConvert.str2Obj(message.toString(), ChatMessage.class);
        chatMessageService.handleSendCallback(chatMessage, false, replyText);
    }
}
