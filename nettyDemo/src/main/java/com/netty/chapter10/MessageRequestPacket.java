package com.netty.chapter10;

import com.netty.chapter8.entity.Packet;
import lombok.Data;

import static com.netty.chapter8.command.Command.MESSAGE_REQUEST;

@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

}
