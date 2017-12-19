package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * controller实际上这个注解对Spring MVC本身的影响并不大
 * Controller是一个构造型(stereotype)的注解,它基于@Component注解。在这里,它的目的就是辅助实现组件扫描。因为HomeController带有@Controller注解,因此组件扫描器会自动找到HomeController,并将其声明为Spring应用上下文中的一个bean。
 * 其实,你也可以让HomeController带有@Component注解,它所实现的效果是一样的,但是在表意性上可能会差一些,无法确定HomeController是什么组件类型。
 */

@Controller
@RequestMapping({"/", "/homepage"})
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "home";
    }

}
