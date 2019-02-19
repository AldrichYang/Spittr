package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import service.SpitterService;

import java.util.Properties;

/**
 * Created by yh on 17/11/21.
 */
@Configuration
@ComponentScan(basePackages = {"dao", "biz", "service"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {

    /**
     * 导出Hessian服务
     *
     * @param spitterService
     * @return
     */
    @Bean
    public HessianServiceExporter hessianExportedSpitterService(SpitterService spitterService) {
        HessianServiceExporter exporter = new HessianServiceExporter();
//        service属性设置为实现了这个服务的bean引用
        exporter.setService(spitterService);
//        serviceInterface属性标识这个服务实现了SpitterSerivce接口
        exporter.setServiceInterface(SpitterService.class);
        return exporter;
    }

    /**
     * 由于Hessian是基于HTTP的，所以HessianServiceExporter实现为一个SpringMVC控制器。
     * 因此为了导出Hessian服务，还需要两步骤：
     * 1. 配置DispatcherServlet，把应用部署为Web应用
     * 2. 配置一个URL处理器，把Hessian服务的URL分发给对应的Hessian服务Bean
     *
     * @return
     */
    @Bean
    public HandlerMapping hessianMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        Properties mappings = new Properties();
        mappings.setProperty("/spitter.service", "hessianExportedSpitterService");
        mapping.setMappings(mappings);
        mapping.setOrder(4);
        return mapping;
    }

    /**
     * 访问Hessian服务的Bean，一般应该在另一个客户端应用中，此处偷懒
     *
     * @return
     */
    @Bean
    public HessianProxyFactoryBean spitterService() {
        HessianProxyFactoryBean proxyFactoryBean = new HessianProxyFactoryBean();
//        serviceUrl标示了这个服务的URL，Hessian是基于HTTP的，这里就是Http URL
        proxyFactoryBean.setServiceUrl("http://localhost:8080/spittr/spitter.service");
        proxyFactoryBean.setServiceInterface(SpitterService.class);
        return proxyFactoryBean;
    }

    /**
     * 把SpitterService通过HttpInovker的方式导出为远程服务
     *
     * @param spitterService
     * @return
     */
//    @Bean
//    public HttpInvokerServiceExporter httpExportedSpitterService(SpitterService spitterService) {
//        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
//        exporter.setService(spitterService);
//        exporter.setServiceInterface(SpitterService.class);
//        return exporter;
//    }

    /**
     * 配置HttpInovker模式下的HandlerMapping
     *
     * @return
     */
//    @Bean
//    public HandlerMapping httpInvokerMapping() {
//        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//        Properties mappings = new Properties();
//        mappings.setProperty("/spitter.service", "httpExportedSpitterService");
//        mapping.setMappings(mappings);
//        return mapping;
//    }

    /**
     * 访问HttpInvoker导出的远程服务
     *
     * @return
     */
//    @Bean
//    public HttpInvokerProxyFactoryBean spitterService1() {
//        HttpInvokerProxyFactoryBean proxyFactoryBean = new HttpInvokerProxyFactoryBean();
//        proxyFactoryBean.setServiceUrl("http://localhost:8080/spittr/spitter.service");
//        proxyFactoryBean.setServiceInterface(SpitterService.class);
//        return proxyFactoryBean;
//    }


}
