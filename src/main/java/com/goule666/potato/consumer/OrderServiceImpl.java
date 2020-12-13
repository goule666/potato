package com.goule666.potato.consumer;

import com.alibaba.fastjson.JSON;
import com.goule666.potato.domain.UserInfoDTO;
import com.goule666.potato.provider.UserService;

/**
 * @author wenlongnie
 * @date 2020-10-02 17:36
 * @description
 **/
public class OrderServiceImpl implements OrderService {
    private UserService userService;


    @Override
    public void create(Long userId) {
        userService = (UserService) ClientMain.hashMap.get(UserService.class.getName());
        //调用用户服务查询用户信息
        UserInfoDTO userInfoDTO = userService.findByUserId(1L);
        System.out.println("远程调用结束" + JSON.toJSONString(userInfoDTO));

    }
}
