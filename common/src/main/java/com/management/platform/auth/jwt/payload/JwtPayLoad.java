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
 *
 * jwt工作原理：在认证的时候，当用户用他们的凭证成功登录以后，一个JSON Web Token将会被返回。此后，token就是用户凭证了
 * token验证与session验证的区别：
 *Sessions : 每次用户认证通过以后，服务器需要创建一条记录保存用户信息，通常是在内存中，随着认证通过的用户越来越多，服务器的在这里的开销就会越来越大。
 * Scalability : 由于Session是在内存中的，这就带来一些扩展性的问题。
 * CORS : 当我们想要扩展我们的应用，让我们的数据被多个移动设备使用时，我们必须考虑跨资源共享问题。当使用AJAX调用从另一个域名下获取资源时，我们可能会遇到禁止请求的问题。
 * CSRF : 用户很容易受到CSRF攻击。
 *
 * 相同点是，它们都是存储用户信息；然而，Session是在服务器端的，而JWT是在客户端的。
 * Session方式存储用户信息的最大问题在于要占用大量服务器内存，增加服务器的开销。
 * 而JWT方式将用户状态分散到了客户端中，可以明显减轻服务端的内存压力。
 * Session的状态是存储在服务器端，客户端只有session id；而Token的状态是存储在客户端。
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
