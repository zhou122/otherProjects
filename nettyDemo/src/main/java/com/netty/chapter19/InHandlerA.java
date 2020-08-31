package com.netty.chapter19;

import com.netty.chapter17.protocol.response.LoginResponsePacket;
import com.netty.chapter17.util.IDUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class InHandlerA extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("===InHandlerA"+msg);
        /*super.channelRead(ctx, msg);*/

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(new Byte("2"));
        loginResponsePacket.setUserName("周");
        loginResponsePacket.setSuccess(true);
        loginResponsePacket.setUserId(IDUtil.randomId());
        // 登录响应
        //ctx.channel().writeAndFlush(loginResponsePacket);
        ctx.writeAndFlush(loginResponsePacket);
    }
}
