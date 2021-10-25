package com.javashitang.wshandler;

import com.javashitang.utils.CommonUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleUserEventChannelHandler;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class HandShakeEventHandler extends SimpleUserEventChannelHandler<WebSocketServerProtocolHandler.HandshakeComplete> {

    // 建立连接后进行参数校验
    @Override
    protected void eventReceived(ChannelHandlerContext ctx, WebSocketServerProtocolHandler.HandshakeComplete evt) throws Exception {
        // 进行参数校验
        if (dealHandsharkComplete()) {
            ctx.fireUserEventTriggered(evt);
        } else {
            CommonUtil.authFailed(ctx, "参数错误");
        }
    }

    // 处理websocket握手完成的事件
    private boolean dealHandsharkComplete() {
        return true;
    }
}
