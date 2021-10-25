package com.javashitang.processor;

import com.javashitang.domain.ChatMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author lilimin
 * @since 2021-10-25
 */
public interface MessageProcessor {

    void process(ChannelHandlerContext ctx, ChatMessage chatMessage);
}
