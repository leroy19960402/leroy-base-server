package com.leroy.base.core.user.api;

import com.leroy.base.core.common.vo.CommonResult;
import com.leroy.base.core.user.entity.User;
import com.leroy.base.core.user.service.UserService;
import com.leroy.base.core.user.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(UserController.PATH)
public class UserController {

    public static final String PATH = "/user";

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param response
     * @param request
     * @param userVo
     * @return
     */
    @PostMapping("/login")
    public CommonResult Login(HttpServletResponse response, HttpServletRequest request, @RequestBody UserVo userVo) {
        CommonResult commonResult = new CommonResult();
        try {
            User user2 = userService.userlogin(request, response, userVo);
            if (user2 != null) {
                commonResult.setState(200);
                commonResult.setMsg("登录成功！");
                return commonResult;
            } else {
                commonResult.setState(202);
                commonResult.setMsg("用户名或密码错误！");
                return commonResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonResult.setState(500);
            commonResult.setMsg("发生错误，登录失败！");
            return commonResult;
        }
    }

    /**
     * 判断是否登录
     *
     * @param response
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/toLogin")
    public CommonResult getUserInfo(HttpServletResponse response, HttpServletRequest request) throws Exception {
        CommonResult commonResult = new CommonResult();
        try {
            String token = userService.getUserByToken(response, request);
            if (token != null) {
                commonResult.setState(200);
                commonResult.setMsg("登录中！");
                return commonResult;
            } else {
                commonResult.setState(202);
                commonResult.setMsg("在别处登录！");
                return commonResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            commonResult.setMsg("发生错误！");
            return commonResult;
        }
    }

    @PostMapping("/addUser")
    public CommonResult addUser(@RequestBody UserVo userVo) throws Exception {
        CommonResult result = new CommonResult();
        try {
            userService.addUser(userVo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }

    @PostMapping("/updatePassWord")
    public CommonResult updatePassWord(@RequestBody UserVo userVo) throws Exception {
        CommonResult result = new CommonResult();
        try {
            userService.updatePassWord(userVo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }
}
