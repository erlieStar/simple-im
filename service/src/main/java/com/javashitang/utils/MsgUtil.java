package com.javashitang.utils;

import com.javashitang.domain.ChatMessage;

public class MsgUtil {

    public static final String KICKOUT = "您已经在别的设备登陆，此设备下线";
    public static final String MSG_ATTR_SERNDER = "sender";
    public static final String MSG_ATTR_RECEIVER = "receiver";

    public static ChatMessage authFailMsg(String receiver, String msg) {
        ChatMessage chatMessage = new ChatMessage();
        return chatMessage;
    }

    public static void setMsgAckOrNot(ChatMessage message, boolean ack) {
    }
}
