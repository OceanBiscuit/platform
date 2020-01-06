package com.management.platform.core.auth.cache.impl;

import com.management.platform.auth.model.LoginUser;
import com.management.platform.core.auth.cache.SessionManager;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于内存的会话管理 这里直接将创建，获取，移除，判断用map库来代替，也就是对内存的操作
 * <p>
 * ps:您可以自行拓展内存管理哦
 *
 * @author fengshuonan
 * @date 2019-09-28-14:43
 */
@Component
public class DefaultSessionManager implements SessionManager {

    private Map<String, LoginUser> caches = new ConcurrentHashMap<>();

    @Override
    public void createSession(String token, LoginUser loginUser) {
        caches.put(SESSION_PREFIX + token, loginUser);
    }//将用户信息绑定到session

    @Override
    public LoginUser getSession(String token) {
        return caches.get(SESSION_PREFIX + token);
    }//获取session用户信息

    @Override
    public void removeSession(String token) {
        caches.remove(SESSION_PREFIX + token);
    }//清空session

    @Override
    public boolean haveSession(String token) {
        return caches.containsKey(SESSION_PREFIX + token);
    }//是否有session判断是否已经登陆
}
