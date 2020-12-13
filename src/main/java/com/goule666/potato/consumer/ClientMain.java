package com.goule666.potato.consumer;

import com.goule666.potato.provider.UserService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wenlongnie
 * @date 2020-10-02 16:40
 * @description
 **/
public class ClientMain {
    public static ConcurrentHashMap hashMap = new ConcurrentHashMap();


    public ClientMain() {
        UserServiceImplProxy proxy = new UserServiceImplProxy();
        UserService userService = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserService.class}, proxy);
        hashMap.put(UserService.class.getName(), userService);

        OrderService orderService = new OrderServiceImpl();
        hashMap.put(OrderService.class.getName(), orderService);

    }


    public ChannelFuture run() throws InterruptedException, UnsupportedEncodingException {
        EventLoopGroup group = new NioEventLoopGroup();


        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        ch.pipeline().addLast(new PotatoClientHandler());
                    }
                });
        //从注册中心获取服务端ip和端口
        ChannelFuture f = bootstrap.connect("127.0.0.1", 11111).sync();
        Thread.sleep(2000);
        hashMap.put("f", f);
        return f;

    }

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        ClientMain clientMain = new ClientMain();

        new Thread(() -> {
            try {
                clientMain.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(3000);

        OrderService orderService = (OrderService) hashMap.get(OrderService.class.getName());
        orderService.create(1L);

    }
}
