package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by yh on 17/11/21.
 */
@Configuration
@EnableWebMvc   // 启用SpringMvc
@ComponentScan(basePackages = {"controller"})   // 启用组件扫描
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
// 将视图解析为Web应用的内部资源(一般为JSP)
// InternalResourceViewResolver所采取的方式并不那么直接。它遵循一种约定,会在视图名上添加前缀和后缀,进而确定一个Web应用中视图资源的物理路径
// 如果这些JSP使用JSTL标签来处理格式化和信息的话,那么我们会希望InternalResourceViewResolver将视图解析为JstlView
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //我们要求DispatcherServlet将对静态资源的请求转发到Servlet容器中默认的Servlet上,而不是使用DispatcherServlet本身来处理此类请求。
        configurer.enable();
    }


}
