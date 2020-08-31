package com.netty.chapter19;

import com.netty.chapter17.protocol.Packet;
import com.netty.chapter17.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) {
        System.out.println("===PacketEncoder");
        PacketCodeC.INSTANCE.encode(out, packet);
    }
}
