package cn.czy15.zyweb.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author Zhuoyu Chen
 * @date 2019/12/28 0028 - 13:34
 */
@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class TokenSettings {
    private String secretKey;
    private Duration accessTokenExpireTime;
    private Duration refreshTokenExpireTime;
    private Duration refreshTokenExpireAppTime;
    private String issuer;
}

