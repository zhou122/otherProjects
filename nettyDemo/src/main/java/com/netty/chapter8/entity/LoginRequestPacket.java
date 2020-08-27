package com.netty.chapter8.entity;

import lombok.Data;

import static com.netty.chapter8.command.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet{

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
