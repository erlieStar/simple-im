package com.javashitang.common;

import java.util.Arrays;
import java.util.List;

public class EnumDefine {

    enum USER_STATUS {

        ONLINE((byte)1, "在线");

        private final byte value;
        private final String name;

        USER_STATUS(byte value, String name) {
            this.value = value;
            this.name = name;
        }
    }


    enum CHAT_TYPE {

        SINGLE((byte) 0, "单聊", Arrays.asList(MSG_TYPE.TEXT)),
        INSTRUCTION((byte) 1, "指令", Arrays.asList(MSG_TYPE.ACK, MSG_TYPE.NACK));

        private final byte value;
        private final String name;
        private final List<MSG_TYPE> msgList;

        CHAT_TYPE(byte value, String name, List<MSG_TYPE> msgList) {
            this.value = value;
            this.name = name;
            this.msgList = msgList;
        }
    }

    enum MSG_TYPE {

        UNKNOW((byte) 0, "未知"),
        TEXT((byte) 10 , "文本"),

        // 指令用到的类型
        ACK((byte) 53, "消息确认"),
        NACK((byte) 54, "消息拒绝")
        ;
        private final byte value;
        private final String name;

        MSG_TYPE(byte value, String name) {
            this.value = value;
            this.name = name;
        }
    }
}
