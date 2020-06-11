package com.leroy.base.core.user.service;


import com.leroy.base.core.user.entity.User;
import com.leroy.base.core.user.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    /**
     * 登录
     * @param request
     * @param response
     * @param userVo
     * @return
     */
    User userlogin(HttpServletRequest request, HttpServletResponse response, UserVo userVo);
    /**
     * 判断是否登录
     * @param response
     * @param request
     * @return
     */
    String getUserByToken(HttpServletResponse response, HttpServletRequest request);
    /**
     * @param userVo
     * @return
     * @description 增加用户
     * @author lideyou
     * @date 2020年6月8日15:27:14
     */
    void addUser(UserVo userVo);
    /**
     * @param userVo
     * @return
     * @description 修改密码
     * @author lideyou
     * @date 2020年6月8日15:27:14
     */
    void updatePassWord(UserVo userVo);
}
