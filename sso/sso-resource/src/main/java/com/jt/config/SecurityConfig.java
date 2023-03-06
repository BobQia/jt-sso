package com.jt.config;

import com.jt.util.WebUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable();
         http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler());
        http.authorizeRequests().anyRequest().permitAll();
    }
    //没有权限时执行此处理器方法
    public AccessDeniedHandler accessDeniedHandler(){
        return (httpServletRequest, httpServletResponse, e)-> {
                Map<String,Object> map=new HashMap<>();
                map.put("state",403);//SC_FORBIDDEN的值是403
                map.put("message","没有访问权限,请联系管理员");
                WebUtils.writeJsonToClient(httpServletResponse,map);

        };
    }
}
