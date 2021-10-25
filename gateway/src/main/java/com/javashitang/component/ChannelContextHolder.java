package com.javashitang.component;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ChannelContextHolder {

    // 保存 userId -> ChannelHandlerContext 的映射关系
    private Map<String, ChannelHandlerContext> ctxMap = new ConcurrentHashMap<>();

    public ChannelHandlerContext getChannelByUserId(String userId) {
        return ctxMap.get(userId);
    }

}
