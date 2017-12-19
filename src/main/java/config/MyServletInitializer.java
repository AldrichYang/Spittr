package config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * AbstractAnnotationConfigDispatcherServletInitializer的定义,它会创建DispatcherServlet和ContextLoaderListener。
 * 但是,如果你想注册其他的Servlet、 Filter或Listener的话,基于Java的初始化器(initializer)的一个好处就在于我们可以定义任意数量的初始化器类
 */
public class MyServletInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        DispatcherServlet ds = new DispatcherServlet();
//        ServletRegistration.Dynamic registration = servletContext.addServlet("appServlet", ds);
//        registration.addMapping("/");
//        registration.setMultipartConfig(new MultipartConfigElement("/tmp/spittr/uploads"));

//        servletContext.addServlet();
//        servletContext.addFilter();

    }
}
