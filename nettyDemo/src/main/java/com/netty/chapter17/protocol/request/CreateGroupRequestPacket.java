package com.netty.chapter17.protocol.request;

import lombok.Data;
import com.netty.chapter17.protocol.Packet;

import java.util.List;

import static com.netty.chapter17.protocol.command.Command.CREATE_GROUP_REQUEST;

@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_REQUEST;
    }
}
