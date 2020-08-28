package com.netty.chapter17.protocol.response;

import lombok.Data;
import com.netty.chapter17.protocol.Packet;

import static com.netty.chapter17.protocol.command.Command.LOGOUT_RESPONSE;

@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE;
    }
}
