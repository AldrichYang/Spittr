package config.initializer;

import config.RootConfig;
import config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * 扩展AbstractAnnotationConfigDispatcherServlet- Initializer快速搭建了Spring MVC环境
 * 这个便利的基础类中,假设我们需要基本的DispatcherServlet和ContextLoaderListener环境,并且Spring配置是使用Java的, 而不是XML
 */

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * @return
     */
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    /**
     * 我们要求DispatcherServlet加载Spring应用上下文
     *
     * @return
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    /**
     * 将一个或多个路径映射到DispatcherServlet上
     *
     * @return
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 此类的方法之一就是customizeRegistration()。在AbstractAnnotationConfigDispatcherServletInitializer将DispatcherServlet注册到Servlet容器中之后,
     * 就会调用customizeRegistration(),并将Servlet注册后得到 的Registration.Dynamic传递进来。
     * 通过重载customizeRegistration()方法,我们可 以对DispatcherServlet进行额外的配置
     *
     * @param registration
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(
                new MultipartConfigElement("/tmp/spittr/uploads")
        );
    }

    /**
     * 返回的所有Filter都会映射到DispatcherServlet上
     *
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{};
    }
}
