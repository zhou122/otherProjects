package com.netty.chapter17.protocol.request;

import lombok.Data;
import com.netty.chapter17.protocol.Packet;

import static com.netty.chapter17.protocol.command.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet {
    private String userName;

    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}
