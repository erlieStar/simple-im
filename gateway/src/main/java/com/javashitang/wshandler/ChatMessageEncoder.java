package com.javashitang.wshandler;

import com.javashitang.domain.ChatMessage;
import com.javashitang.tool.JsonConvert;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

@ChannelHandler.Sharable
public class ChatMessageEncoder extends MessageToMessageEncoder<ChatMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ChatMessage msg, List<Object> out) throws Exception {
        String text = JsonConvert.obj2Str(msg);
        TextWebSocketFrame frame = new TextWebSocketFrame(text);
        out.add(frame);
    }
}
