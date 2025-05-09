package com.redis.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-05-09  9:52
 */
public class JWTUtils {
      public static final String SIGN = "TTTWWWGGGXXFRERE";
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);

        JWTCreator.Builder  builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });

        String sign = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));

        return sign;
    }

    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    public static DecodedJWT getTokenInfo(String token){
       DecodedJWT jwt =  JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
       return jwt;
    }
}
