package com.netty.chapter12.server;

import com.netty.chapter10.ServerHandler;
import com.netty.chapter12.serializer.PacketDecoder;
import com.netty.chapter12.serializer.PacketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyServer {

    public static void main(String[] args){
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>(){
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new PacketDecoder());//解码
                        nioSocketChannel.pipeline().addLast(new LoginRequestHandler());//登录请求
                        nioSocketChannel.pipeline().addLast(new MessageRequestHandler());//消息请求
                        nioSocketChannel.pipeline().addLast(new PacketEncoder());//编码
                    }
                });
        bind(serverBootstrap,8000);
    }

    /**
     * 递增方式尝试端口绑定
     * @param serverBootstrap
     * @param port
     */
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
}
