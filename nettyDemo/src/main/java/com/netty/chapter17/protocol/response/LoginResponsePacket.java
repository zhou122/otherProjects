package com.netty.chapter17.protocol.response;

import lombok.Data;
import com.netty.chapter17.protocol.Packet;

import static com.netty.chapter17.protocol.command.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {
    private String userId;

    private String userName;

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGIN_RESPONSE;
    }
}
