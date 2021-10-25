package com.javashitang.service.impl;

import com.javashitang.domain.ChatMessage;
import com.javashitang.domain.UserLoginInfo;
import com.javashitang.service.inf.ChatMessageService;
import com.javashitang.service.inf.ImLoginService;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ImLoginServiceImpl implements ImLoginService {

    @Autowired
    ChatMessageService chatMessageService;

    @Override
    public void onMessage(ChannelHandlerContext ctx, UserLoginInfo loginInfo, ChatMessage msg) {
        try {
            boolean handled = chatMessageService.prepareHandleMessage(ctx, loginInfo, msg);
            if (handled) {
                chatMessageService.handleMessage(ctx, loginInfo, msg);
            }
        } catch (Exception e) {
            log.error("onMessage error ", e);
        } finally {
            chatMessageService.postHandleMessage(ctx, loginInfo, msg);
        }
    }
}
