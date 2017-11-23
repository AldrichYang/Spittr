package controller;

import dao.SpittleRepository;
import model.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by yh on 17/11/22.
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model) {
//        model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        //当调用addAttribute()方法并且不指定key的时候,那么key会根据值的对象类型推断确定。
        // 在本例中,因为它是一个List<Spittle>,因此,键将会推断为spittleList。
        model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        return "spittles";
    }
//
//    这个版本与其他的版本有些差别。它并没有返回视图名称,也没有显式地设定模型,这个方法返回的是Spittle列表。
//    当处理器方法像这样返回对象或集合时,这个值会放到模型中,模型的key会根据其类型推断得出(在本例中,也就是spittleList)。
//    而逻辑视图的名称将会根据请求路径推断得出。因为这个方法处理针对“/spittles”的GET请求,因此 视图的名称将会是spittles(去掉开头的斜线)。
    public List<Spittle> spittles() {
        return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
    }


}
