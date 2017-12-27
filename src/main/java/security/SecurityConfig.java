package security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by yh on 17/12/19.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 通过重载,配置Spring Security的Filter链
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 通过重载,配置如何通过拦截器保护请求
     * 配置如何保护HTTP请求,以及客户端认证用户的方案，支持基于表单的登录以及HTTP Basic方式的认证
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.formLogin().and().authorizeRequests().anyRequest().permitAll();
    }

    /**
     * 通过重载，配置用户存储
     * 配置用户存储支撑认证过程
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
//        内存用户存储
//        auth.inMemoryAuthentication().withUser("user").password("password").roles("user").and()
//                .withUser("admin").password("password").roles("user", "admin");
        auth.userDetailsService(new MyUserService());
    }
}

