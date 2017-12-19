package controller;

import dao.SpittleRepository;
import infra.exception.DuplicateSpittleException;
import infra.exception.SpittleNotFoundException;
import model.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by yh on 17/11/22.
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    /**
     * Only certain types of operations can be used in a constant expression. A method call, such as Long.toString(), isn't one of those
     * private static final String MAX_LONG_AS_STRING = Long.toString(Long.MAX_VALUE);
     */
    private static final String MAX_LONG_AS_STRING = Long.MAX_VALUE + "";

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    /**
     * 处理查询参数
     *
     * @param max
     * @param count
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
                           @RequestParam(value = "count", defaultValue = "20") int count,
                           Model model) {
//        当调用addAttribute()方法并且不指定key的时候,那么key会根据值的对象类型推断确定。在本例中,因为它是一个List<Spittle>,因此,键将会推断为spittleList。
//        model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        model.addAttribute(spittleRepository.findSpittles(max, count));

        return "spittles";
    }

    /**
     * 这个版本与其他的版本有些差别。它并没有返回视图名称,也没有显式地设定模型,这个方法返回的是Spittle列表。
     * 当处理器方法像这样返回对象或集合时,这个值会放到模型中,模型的key会根据其类型推断得出(在本例中,也就是spittleList)。
     * 而逻辑视图的名称将会根据请求路径推断得出。因为这个方法处理针对“/spittles”的GET请求,因此 视图的名称将会是spittles(去掉开头的斜线)
     *
     * @return
     */
    @RequestMapping(path = "/spittles1", method = RequestMethod.GET)
    public List<Spittle> spittles1() {
        return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
    }

    /**
     * 通过查询参数接受输入
     *
     * @param spittleId
     * @param model
     * @return
     */
    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String showSpittle1(
            @RequestParam("spittle_id") long spittleId,
            Model model) {
        model.addAttribute(spittleRepository.findOne(spittleId));
        return "spittle";
    }


    /**
     * 通过路径参数接受输入
     * 实现这种路径变量,Spring MVC允许我们在@RequestMapping路径中添加占位符。占位符的名称要用大括号(“{”和“}”)括起来。
     * 路径中的其他部分要与所处理的请求完全匹配,但是占位符部分可以是任意的值
     * <p>
     * 在样例中spittleId这个词出现了好几次:先是在@RequestMapping的路径中,然后作为@PathVariable属性的值,最后又作为方法的参数名称。
     * 因为方法的参数名碰巧与占位符的名称相同,因此我们可以去掉@PathVariable中的value属性 例如：@PathVariable long spittleId
     * 如果@PathVariable中没有value属性的话,它会假设占位符的名称与方法的参数名相同
     *
     * @param spittleId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String showSpittle(
            @PathVariable("spittleId") long spittleId,
            Model model) {

        Spittle spittle = spittleRepository.findOne(spittleId);
        if (Objects.isNull(spittle)) {
//       如果出现任何没有映射的异常,响应都会带有500状态码
            throw new SpittleNotFoundException();
        }
        model.addAttribute(spittle);
        return "spittle";
    }

    @RequestMapping(path = "/saveSpittle", method = RequestMethod.POST)
    public String saveSpittle(Spittle form, Model model) {
        spittleRepository.save(new Spittle(form.getMessage(), new Date(), form.getLongitude(), form.getLatitude()));
        return "redirect:/spittles";
    }

    /**
     * 对于@ExceptionHandler注解标注的方法来说,比较有意思的一点在于它能处理同一个控制器中所有处理器方法所抛出的异常
     *
     * @return
     */
    @ExceptionHandler(DuplicateSpittleException.class)
    public String handleDuplicateSpittle() {
        return "error/duplicate";

    }

}
