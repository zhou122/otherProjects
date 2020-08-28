package com.netty.chapter17.protocol.request;

import lombok.Data;
import com.netty.chapter17.protocol.Packet;

import static com.netty.chapter17.protocol.command.Command.LOGOUT_REQUEST;

@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {

        return LOGOUT_REQUEST;
    }
}
