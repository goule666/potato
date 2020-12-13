package com.goule666.potato.provider;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wenlongnie
 * @date 2020-10-02 16:09
 * @description
 **/
public class ServerMain {

    public static ConcurrentHashMap hashMap = new ConcurrentHashMap();

    public ServerMain() {
        UserService userService = new UserServiceImpl();
        hashMap.put(UserService.class.getName(), userService);
    }

    public void run(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new PotatoServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync();

            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ServerMain serverMain = new ServerMain();
        serverMain.run(11111);
    }
}
