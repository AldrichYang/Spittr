package controller;

import infra.exception.DuplicateSpittleException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 控制器通知(controller advice)是 任意带有@ControllerAdvice注解的类,这个类会包含一个或多个如下类型的方法:
 * <p>
 * ExceptionHandler注解标注的方法;
 * InitBinder注解标注的方法;
 * ModelAttribute注解标注的方法。 在带有@ControllerAdvice注解的类中,以上所述的这些方法会运用到整个应用程序所有控制器中带有@RequestMapping注解的方法上。
 * ControllerAdvice注解本身已经使用了@Component,因此@ControllerAdvice注解所 标注的类将会自动被组件扫描获取到, 就像带有@Component注解的类一样
 * ControllerAdvice最为实用的一个场景就是将所有的@ExceptionHandler方法收集到一 个类中, 这样所有控制器的异常就能在一个地方进行一致的处理
 */
@ControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(DuplicateSpittleException.class)
    public String handleDuplicateSpittle() {
        return "error/duplicate";

    }

}
