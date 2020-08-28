package com.netty.chapter13;

import com.netty.chapter12.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * LengthFieldBasedFrameDecoder基于长度域拆包器
 * 是最通用的一种拆包器，只要你的自定义协议中包含长度域字段，均可以使用这个拆包器来实现应用层拆包
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        /**
         * --------------------------------------------------------------------------------------------------------
         * 魔数（4字节）  | 版本号（1字节） |  序列化算法（1字节） | 指令（1字节） | 数据长度（4字节） | 数据（N字节） |
         * --------------------------------------------------------------------------------------------------------
         * 第一个参数是数据包的最大长度
         * 第二个参数是长度域相对整个数据包的偏移量是多少，这里显然是 4+1+1+1=7
         * 第三个参数指的是长度域的长度
         */
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    /**
     * 拒绝非本协议连接
     * 第二个参数in 每次传递进来的时候，均为一个数据包的开头
     * @param ctx
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 屏蔽非本协议的客户端
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }

}
