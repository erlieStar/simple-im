package com.javashitang.component.others;

import com.javashitang.wshandler.GatewayChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class ServerStarter {

    @Value("im.server.websocket.port")
    private int serverPort;
    private EventLoopGroup boss;
    private EventLoopGroup work;

    @Autowired
    private GatewayChannelInitializer gatewayChannelInitializer;

    @PostConstruct
    public void init() {
        boss = new NioEventLoopGroup();
        work = new NioEventLoopGroup();
        startServer();
    }

    public void startServer() {
        boss = new EpollEventLoopGroup();
        work = new EpollEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        ChannelFuture future = bootstrap.group(boss, work).handler(new LoggingHandler(LogLevel.DEBUG))
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT).childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT).childHandler(gatewayChannelInitializer)
                .bind(serverPort);

        future.addListener(f -> {
            log.info("server is start, port: {}, status: {}", serverPort, future.isSuccess() ? "success" : "failed");
        });
    }

    @PreDestroy
    public void destory() {
        if (boss != null) {
            boss.shutdownGracefully();
        }
        if (work != null) {
            work.shutdownGracefully();
        }
    }
}
