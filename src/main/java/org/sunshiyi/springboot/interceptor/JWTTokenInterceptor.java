package org.sunshiyi.springboot.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.sunshiyi.springboot.utils.JwtUtil;
import org.sunshiyi.springboot.utils.ThreadLocalUtil;

import java.util.Map;

@Component
public class JWTTokenInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate template;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");
        try {
            String s = template.opsForValue().get(token);
            if (s == null) {
                // redis 中没有对应的JWT TOKEN
                response.setStatus(401);
                return false;
            }
            Map<String, Object> claims = JwtUtil.parseToken(token);
            ThreadLocalUtil.set(claims);
        } catch (Exception e) {
            // JWT Token校验失败
            response.setStatus(401);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ThreadLocalUtil.remove();
    }
}
