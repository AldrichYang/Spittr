package controller;

import infra.exception.BizError;
import infra.exception.DuplicateSpittleException;
import infra.exception.SpittleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 控制器通知(controller advice)是 任意带有@ControllerAdvice注解的类,这个类会包含一个或多个如下类型的方法:
 * <p>
 * ExceptionHandler注解标注的方法;
 * InitBinder注解标注的方法;
 * ModelAttribute注解标注的方法。 在带有@ControllerAdvice注解的类中,以上所述的这些方法会运用到整个应用程序所有控制器中带有@RequestMapping注解的方法上。
 * <p>
 * ControllerAdvice注解本身已经使用了@Component,因此@ControllerAdvice注解所标注的类将会自动被组件扫描获取到, 就像带有@Component注解的类一样
 * ControllerAdvice最为实用的一个场景就是将所有的@ExceptionHandler方法收集到一个类中, 这样所有控制器的异常就能在一个地方进行一致的处理
 */
@ControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(SpittleNotFoundException.class)
    public ResponseEntity<BizError> sipttleNotFound(SpittleNotFoundException e) {
        long spittleId = e.getId();
        BizError error = new BizError(4, "Spittle[" + spittleId + "] not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SpittleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public BizError sipttleNotFound1(SpittleNotFoundException e) {
        long spittleId = e.getId();
        BizError error = new BizError(4, "Spittle[" + spittleId + "] not found");
        return error;
    }

    @ExceptionHandler(DuplicateSpittleException.class)
    public String handleDuplicateSpittle() {
        return "error/duplicate";

    }


}
