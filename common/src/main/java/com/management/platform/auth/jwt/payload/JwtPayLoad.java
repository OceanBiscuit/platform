package com.management.platform.auth.jwt.payload;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt的第二部分
 * 第一部分为Header:典型由两部分组成，token类型（JWT）和算法名称（比如：HMAC SHA256或者RSA等等）。
 *例如：
 * {
 * "alg":"HS256 ,
 * "type":"JWT"
 *}BASE64对这个header的json进行编码得到第一部分
 *
 * 第二部分就是payload
 * 例如：
 * {
 *     "sub":"1231231"
 *     "name":"john doe",
 *     "admin":true
 * }BASE64对这个payload的json进行编码得到第二部分
 *
 * 第三部分：
 * HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret)
 * 签名是用于验证消息在传递过程中有没有被更改，并且，对于使用私钥签名的token，它还可以验证JWT的发送方是否为它所称的发送方。
 * @author fengshuonan
 * @Date 2019/7/20 20:45
 */
@Data
public class JwtPayLoad {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户的键
     */
    private String userKey;

    public JwtPayLoad() {
    }

    public JwtPayLoad(Long userId, String account, String userKey) {
        this.userId = userId;
        this.account = account;
        this.userKey = userKey;
    }

    /**
     * payload转化为map形式
     *
     * @author fengshuonan
     * @Date 2019/7/20 20:50
     */
    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", this.userId);
        map.put("account", this.account);
        map.put("userKey", this.userKey);
        return map;
    }

    /**
     * payload转化为map形式
     *
     * @author fengshuonan
     * @Date 2019/7/20 20:50
     */
    public static JwtPayLoad toBean(Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            return new JwtPayLoad();
        } else {
            JwtPayLoad jwtPayLoad = new JwtPayLoad();

            Object userId = map.get("userId");
            if (userId instanceof Integer) {
                jwtPayLoad.setUserId(Long.valueOf(map.get("userId").toString()));
            }

            jwtPayLoad.setAccount((String) map.get("account"));
            jwtPayLoad.setUserKey((String) map.get("userKey"));
            return jwtPayLoad;
        }
    }

}
