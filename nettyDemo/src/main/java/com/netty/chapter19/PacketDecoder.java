package com.netty.chapter19;

import com.netty.chapter17.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) {
        System.out.println("===PacketDecoder");
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}
