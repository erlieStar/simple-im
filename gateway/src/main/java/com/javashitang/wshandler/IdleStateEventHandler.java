package com.javashitang.wshandler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleUserEventChannelHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.stereotype.Component;

/**
 * @author lilimin
 * @since 2021-10-25
 */
@Component
@ChannelHandler.Sharable
public class IdleStateEventHandler extends SimpleUserEventChannelHandler<IdleStateEvent> {

    @Override
    protected void eventReceived(ChannelHandlerContext ctx, IdleStateEvent idleStateEvent) throws Exception {
        switch (idleStateEvent.state()) {
            case READER_IDLE:
                ctx.close();
                break;
            case WRITER_IDLE:
                break;
            case ALL_IDLE:
                break;
        }
    }
}
