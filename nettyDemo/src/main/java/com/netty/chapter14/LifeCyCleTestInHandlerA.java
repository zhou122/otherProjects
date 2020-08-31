package com.netty.chapter14;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 客户端与服务端建立连接时：handlerAdded() -> channelRegistered() -> channelActive() -> channelRead() -> channelReadComplete()
 * 客户端与服务的断开连接时：channelInactive() -> channelUnregistered() -> handlerRemoved()
 *
 * 我们在每次向客户端写数据的时候，都通过 writeAndFlush() 的方法写并刷新到底层，其实这种方式不是特别高效，我们可以在之前调用 writeAndFlush() 的地方都调用 write() 方法，
 * 然后在channelReadComplete里面调用 ctx.channel().flush() 方法，相当于一个批量刷新的机制
 */
public class LifeCyCleTestInHandlerA extends ChannelInboundHandlerAdapter {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("===LifeCyCleTestInHandlerA===逻辑处理器被添加：handlerAdded()");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("===LifeCyCleTestInHandlerA===channel 绑定到线程(NioEventLoop)：channelRegistered()");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("===LifeCyCleTestInHandlerA===channel 准备就绪：channelActive()");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("===LifeCyCleTestInHandlerA===channel 有数据可读：channelRead()");
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("===LifeCyCleTestInHandlerA===channel 某次数据读完：channelReadComplete()");
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("===LifeCyCleTestInHandlerA===channel 被关闭：channelInactive()");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("===LifeCyCleTestInHandlerA===channel 取消线程(NioEventLoop) 的绑定: channelUnregistered()");
        super.channelUnregistered(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("===LifeCyCleTestInHandlerA===逻辑处理器被移除：handlerRemoved()");
        super.handlerRemoved(ctx);
    }

}
