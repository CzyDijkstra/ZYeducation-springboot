package cn.czy15.zyweb.utils;

import org.springframework.stereotype.Component;

/**
 * @author Zhuoyu Chen
 * @date 2019/12/28 0028 - 13:39
 */
@Component
public class InitalizerUtil {
    private TokenSettings tokenSettings;
    public InitalizerUtil(TokenSettings tokenSettings)
    {
        JwtTokenUtil.setTokenSettings(tokenSettings);
    }
}
