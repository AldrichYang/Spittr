package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

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

    /**
     * 配置启用apache tiles 布局功能
     * TilesConfigurer会加载Tile定义并与Apache Tiles协作
     *
     * @return
     */
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tiles = new TilesConfigurer();
//        tiles.setDefinitions("/WEB-INF/layout/tiles.xml"); /*指定Tiles定义的位置*/
        tiles.setDefinitions("/WEB-INF/**/tiles.xml"); /*Ant风格的通配符(**)，TilesConfigurer会遍历“WEB-INF/”的 所有子目录来查找Tile定义*/
        tiles.setCheckRefresh(true);  /*启动自动刷新功能*/

        return tiles;
    }

    /**
     * TilesViewRe-solver会将逻辑视图名称解析为引用Tile定义的视图。它是通过查找与逻辑视图名称相匹配的Tile定义实现该功 能的
     * @return
     */
    @Bean
    public ViewResolver viewResolverTiles() {
        return new TilesViewResolver();
    }


}
