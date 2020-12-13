package com.goule666.potato.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.goule666.potato.domain.UserInfoDTO;
import com.goule666.potato.provider.UserService;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wenlongnie
 * @date 2020-10-02 18:52
 * @description
 **/
public class UserServiceImplProxy implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("12211221");
        if (method.getName().equals("findByUserId")) {
            ChannelFuture future = (ChannelFuture) ClientMain.hashMap.get("f");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("method", method.getName());
            jsonObject.put("args", args[0]);
            jsonObject.put("class", UserService.class);

            System.out.println("开始调用第三方服务");
            future.channel().writeAndFlush(Unpooled.buffer().writeBytes((jsonObject.toJSONString() + "\n").getBytes()));
            ClientMain.hashMap.put("thread", Thread.currentThread());

            synchronized (ClientMain.hashMap) {
                ClientMain.hashMap.wait();
            }

            System.out.println("结束调用第三方服务");

            String string = (String) ClientMain.hashMap.get("result");

            return JSON.parseObject(string, UserInfoDTO.class);
        }
        return null;
    }
}
