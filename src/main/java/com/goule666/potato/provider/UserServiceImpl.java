package com.goule666.potato.provider;

import com.goule666.potato.domain.UserInfoDTO;

/**
 * @author wenlongnie
 * @date 2020-10-02 18:58
 * @description
 **/
public class UserServiceImpl implements UserService {

    public UserInfoDTO findByUserId(Long userId) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId(1L);
        userInfoDTO.setUserName("哈哈哈");
        return userInfoDTO;
    }

}
