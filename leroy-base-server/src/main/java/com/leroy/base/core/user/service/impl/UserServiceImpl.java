package com.leroy.base.core.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.leroy.base.core.common.util.CookieUtils;
import com.leroy.base.core.common.util.RedisUtils;
import com.leroy.base.core.user.dao.UserDao;
import com.leroy.base.core.user.entity.User;
import com.leroy.base.core.user.service.UserService;
import com.leroy.base.core.user.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisUtils redisUtils;
    @Value("${REDIS_KEY}")   //从配置文件中取值
    private String KEY;

    @Override
    public User userlogin(HttpServletRequest request, HttpServletResponse response, UserVo userVo) {
        //查询登录是否成功
        User user = userDao.findByUserNameAndPassWord(userVo.getUserName(), userVo.getPassWord());
        //判断us是否为空
        if (userVo == null) {
            return null;
        }
        //生成token
        String token = "user_" + UUID.randomUUID().toString();
        //从map中获得redis中的key
        String oldToken = (String) redisUtils.get(token);
        //判断map中是否存在该id
        if (!StringUtils.isEmpty(oldToken)) {
            //删除redis中老的值
            redisUtils.del(oldToken);
        }
        //将信息存入redis
        redisUtils.set(token, JSONObject.toJSONString(user));
        //将token放入cookie中
        CookieUtils.setCookie(request, response, KEY, token, 5 * 60, true);
        return user;
    }

    @Override
    public String getUserByToken(HttpServletResponse response, HttpServletRequest request) {
        //从cookie中取出用户token
        String token = CookieUtils.getCookieValue(request, KEY);
        //从redis中取出用户信息
        String user = (String) redisUtils.get(token);
        return user;
    }

    @Override
    public void addUser(UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        userDao.save(user);
    }

    @Override
    public void updatePassWord(UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        String passWord = userVo.getNewPassWord();
        user.setPassWord(passWord);
        userDao.save(user);
    }
}
