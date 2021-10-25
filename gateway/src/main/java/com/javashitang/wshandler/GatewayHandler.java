package com.javashitang.wshandler;

import com.javashitang.domain.ChatMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class GatewayHandler extends SimpleChannelInboundHandler<ChatMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatMessage msg) throws Exception {

    }

    // channel被激活
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    // channel不可用
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    // 处理用户事件
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

    }

    // 发生异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
