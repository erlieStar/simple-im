package com.javashitang.service.inf;

import com.javashitang.domain.ChatMessage;
import com.javashitang.domain.UserLoginInfo;
import io.netty.channel.ChannelHandlerContext;

public interface ImService {

    public void onMessage(ChannelHandlerContext ctx, UserLoginInfo loginInfo, ChatMessage msg);
}
