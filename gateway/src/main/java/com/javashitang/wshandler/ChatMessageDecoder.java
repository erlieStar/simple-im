package com.javashitang.wshandler;

import com.javashitang.domain.ChatMessage;
import com.javashitang.tool.JsonConvert;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;


@ChannelHandler.Sharable
public class ChatMessageDecoder extends MessageToMessageDecoder<TextWebSocketFrame> {

    @Override
    protected void decode(ChannelHandlerContext ctx, TextWebSocketFrame msg, List<Object> out) throws Exception {
        String text = msg.text();
        ChatMessage chatMessage = JsonConvert.str2Obj(text, ChatMessage.class);
        out.add(chatMessage);
    }
}
