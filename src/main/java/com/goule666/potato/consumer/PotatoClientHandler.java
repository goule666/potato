package com.goule666.potato.consumer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author wenlongnie
 * @date 2020-10-02 16:40
 * @description
 **/
public class PotatoClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接上服务器");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("consumer 收到调用信息" + body);
        ClientMain.hashMap.put("result", body);
        synchronized (ClientMain.hashMap) {
            ClientMain.hashMap.notify();
        }

    }


}
