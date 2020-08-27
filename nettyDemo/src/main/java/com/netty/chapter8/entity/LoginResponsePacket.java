package com.netty.chapter8.entity;

import lombok.Data;

import static com.netty.chapter8.command.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet{

    private Byte version;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
