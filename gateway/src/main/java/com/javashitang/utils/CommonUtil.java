package com.javashitang.utils;

import com.javashitang.component.others.ChannelContextHolder;
import com.javashitang.domain.ChatMessage;
import com.javashitang.tool.JsonConvert;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtil {

    public static ChannelFuture authFailed(ChannelHandlerContext ctx, String msg) {
        if (ctx == null) {
            return null;
        }
        Channel channel = ctx.channel();
        ChatMessage failMsg = MsgUtil.authFailMsg("", "");
        return channel.writeAndFlush(failMsg).addListener(ChannelFutureListener.CLOSE);
    }

    // 回传消息
    public static ChannelFuture sendMessageBackToChannel(ChannelContextHolder holder, ChatMessage msg) {
        ChannelHandlerContext ctx = holder.getChannelByUserId(msg.getSender());
        if (ctx == null) {
            log.error("sendMessageBackToChannel error, msg: {}", JsonConvert.obj2Str(msg));
        }
        return sendMessage(ctx, msg);
    }

    // 发送消息
    public static ChannelFuture sendMessage(ChannelHandlerContext ctx, ChatMessage msg) {
        return ctx.channel().writeAndFlush(msg);
    }
}
