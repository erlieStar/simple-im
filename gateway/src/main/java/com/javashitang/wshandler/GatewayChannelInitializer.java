package com.javashitang.wshandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.flow.FlowControlHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GatewayChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Value("{read.idle.timeout}")
    private int readIdleTimeout;
    @Value("{write.idle.timeout}")
    private int writeIdleTimeout;
    @Value("{all.idle.timeout}")
    private int allIdleTimeout;
    @Resource
    private HandShakeEventHandler handShakeEventHandler;
    @Resource
    private IdleStateEventHandler idleStateEventHandler;
    @Resource
    private ChatMessageDecoder chatMessageDecoder;
    @Resource
    private ChatMessageEncoder chatMessageEncoder;
    @Resource
    private DefaultEventLoopGroup asyncEventLoopGroup;
    @Resource
    private GatewayHandler gatewayHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 处理心跳
        pipeline.addLast(new IdleStateHandler(readIdleTimeout, writeIdleTimeout, allIdleTimeout));
        pipeline.addLast(idleStateEventHandler);
        pipeline.addLast(new HttpServerCodec());
        // 聚合http消息
        pipeline.addLast(new HttpObjectAggregator(65535));
        // tcp流量控制
        pipeline.addLast(new FlowControlHandler());
        // 握手事件监听器
        pipeline.addLast(handShakeEventHandler);
        // 二次解码器
        pipeline.addLast(chatMessageDecoder);
        // 二次编码器
        pipeline.addLast(chatMessageEncoder);
        // 业务处理，放在异步线程组中
        pipeline.addLast(asyncEventLoopGroup, gatewayHandler);
    }
}
