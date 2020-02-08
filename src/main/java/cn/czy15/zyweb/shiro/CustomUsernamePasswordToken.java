package cn.czy15.zyweb.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author Zhuoyu Chen
 * @date 2019/12/29 0029 - 12:58
 */
public class CustomUsernamePasswordToken extends UsernamePasswordToken {

    private String token;

    public CustomUsernamePasswordToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}
