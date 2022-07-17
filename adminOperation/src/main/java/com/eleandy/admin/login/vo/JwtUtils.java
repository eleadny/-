package com.eleandy.admin.login.vo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;

@Data
@Configuration
@Accessors(chain = true)
@ConfigurationProperties(prefix = "jwt.config")
public class JwtUtils {
    //签名私钥
    private String key = "userlogin";
    //签名失效时间
    private Long failureTime= 3600L;

    public String createJwt(Integer id, String subject, Map<String, Object> map) {

        //1、设置失效时间啊
        long now = System.currentTimeMillis();  //毫秒
        long exp = now + failureTime;

        //2、创建JwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(String.valueOf(id)).setSubject(subject)
                .setIssuedAt(new Date())
                //设置签名防止篡改
                .signWith(SignatureAlgorithm.HS256,key);

        //3、根据map设置claims
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jwtBuilder.claim(entry.getKey(), entry.getValue());
        }
        jwtBuilder.setExpiration(new Date(exp));

        //4、创建token
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public Claims parseJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }
}
