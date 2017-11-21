package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by yh on 17/11/21.
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    /**
     * 我们要求DispatcherServlet加载应用上下文
     * @return
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    /**
     * 将一个或多个路径映射 到DispatcherServlet上
     * @return
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
