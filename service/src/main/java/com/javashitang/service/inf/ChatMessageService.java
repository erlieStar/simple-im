package com.javashitang.service.inf;

import com.javashitang.domain.ChatMessage;
import com.javashitang.domain.UserLoginInfo;
import io.netty.channel.ChannelHandlerContext;

public interface ChatMessageService {

    // 预处理消息
    boolean prepareHandleMessage(ChannelHandlerContext ctx, UserLoginInfo loginInfo, ChatMessage msg);

    // 处理消息
    boolean handleMessage(ChannelHandlerContext ctx, UserLoginInfo loginInfo, ChatMessage msg);

    // 处理消息完成后执行
    boolean postHandleMessage(ChannelHandlerContext ctx, UserLoginInfo loginInfo, ChatMessage msg);

    /**
     * 处理消息投递的回调类
     * @param message 消息
     * @param ack 是否投递成功
     * @param cause 原因
     */
    void handleSendCallback(ChatMessage message, boolean ack, String cause);

}
