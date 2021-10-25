package com.javashitang.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ChatMessage implements Serializable {

    // 发送者
    private String sender;
    // 接收者
    private String receiver;
    // 消息的唯一标识
    private String msgKey;
    // 消息的时间
    private Long megTime;
    // 消息内容
    private String content;
    // 附加属性
    private Map<String, Object> map;
}
