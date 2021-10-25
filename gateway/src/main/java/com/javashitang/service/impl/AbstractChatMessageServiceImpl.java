package com.javashitang.service.impl;

import com.javashitang.component.others.ChannelContextHolder;
import com.javashitang.domain.ChatMessage;
import com.javashitang.domain.UserLoginInfo;
import com.javashitang.service.inf.ChatMessageService;
import com.javashitang.utils.CommonUtil;
import com.javashitang.utils.MsgUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    protected ChannelContextHolder channelContextHolder;

    @Override
    public boolean prepareHandleMessage(ChannelHandlerContext ctx, UserLoginInfo loginInfo, ChatMessage msg) {
        return false;
    }

    @Override
    public boolean handleMessage(ChannelHandlerContext ctx, UserLoginInfo loginInfo, ChatMessage msg) {
        return false;
    }

    @Override
    public boolean postHandleMessage(ChannelHandlerContext ctx, UserLoginInfo loginInfo, ChatMessage msg) {
        return false;
    }

    /**
     * 处理消息是否正确投递到服务层，将原先的消息类型改为指令消息，ack or nack
     * @param message 消息
     * @param ack 是否投递成功
     * @param cause 原因
     */
    @Override
    public void handleSendCallback(ChatMessage message, boolean ack, String cause) {
        if (ack) {
            MsgUtil.setMsgAckOrNot(message, true);
            CommonUtil.sendMessageBackToChannel(channelContextHolder, message);
            return;
        }
        MsgUtil.setMsgAckOrNot(message, false);
        // 向客户端发送失败消息
        CommonUtil.sendMessageBackToChannel(channelContextHolder, message);
    }
}
