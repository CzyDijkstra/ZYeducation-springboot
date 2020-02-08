package cn.czy15.zyweb.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zhuoyu Chen
 * @project zyweb
 * @date 22:38
 * @des 为了解决跨域的CORS问题
 */
@Component
public class MyAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws Exception {
        WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}