package com.goule666.potato.provider;

import com.goule666.potato.domain.UserInfoDTO;

/**
 * @author wenlongnie
 * @date 2020-10-02 17:38
 * @description
 **/
public interface UserService {
    UserInfoDTO findByUserId(Long userId);
}
