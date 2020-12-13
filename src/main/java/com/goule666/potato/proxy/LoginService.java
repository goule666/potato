package com.goule666.potato.proxy;

/**
 * @author wenlongnie
 * @date 2020-12-13 17:51
 * @description 测试接口
 **/
@TestAnnotation(serviceName = "potato.LoginService.version:1.0.0")
public interface LoginService {
    void login(String mobile);
}
