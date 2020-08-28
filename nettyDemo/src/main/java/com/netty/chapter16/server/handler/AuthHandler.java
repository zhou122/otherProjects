package com.netty.chapter16.server.handler;

import com.netty.chapter16.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AuthHandler extends ChannelInboundHandlerAdapter {

    /**
     * 判断如果已经经过权限认证，那么就直接调用 pipeline 的 remove() 方法删除自身，这里的 this 指的其实就是 AuthHandler 这个对象，
     * 删除之后，这条客户端连接的逻辑链中就不再有这段逻辑了，会回调 handlerRemoved
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            // 一行代码实现逻辑的删除
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

}
