package com.redis.test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-05-09  9:27
 */
public class TestMain {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap();

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,20);
        String sign = JWT.create().withHeader(map)
                .withClaim("userid", "21")
                .withClaim("username", "Tom")
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256("TTTWWWGGGXXFRERE"));
        System.out.println(sign);

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("TTTWWWGGGXXFRERE")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3NDY3NTU3OTAsInVzZXJpZCI6IjIxIiwidXNlcm5hbWUiOiJUb20ifQ.6kTd0bLJQheEXMjopYGkrg5zrBztZk3dKRB4eFPp2M8");
        System.out.println(verify.getClaim("userid").asString());
        System.out.println(verify.getClaim("username").asString());
    }
}
