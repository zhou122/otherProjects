package com.netty.chapter16.protocol.response;

import com.netty.chapter16.protocol.Packet;
import lombok.Data;

import static com.netty.chapter16.protocol.command.Command.MESSAGE_RESPONSE;


@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
