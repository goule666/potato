package com.goule666.potato.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

/**
 * @author wenlongnie
 * @date 2020-10-02 16:19
 * @description 服务端
 **/
public class PotatoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有客户端连接了");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("provider 收到调用信息" + body);
        JSONObject jsonObject = JSON.parseObject(body);
        UserService userService = (UserService) ServerMain.hashMap.get(jsonObject.get("class"));
        String methodName = jsonObject.getString("method");
        Method method = userService.getClass().getMethod(methodName, Long.class);
        Object object = method.invoke(userService, jsonObject.getLong("args"));

        ctx.channel().writeAndFlush(JSON.toJSONString(object) + "\n");
        System.out.println("provider 返回完成, return msg:" + JSON.toJSONString(object));

    }
}
