package controller.rest;

import dao.SpittleRepository;
import model.Spittle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by yh on 2019-03-05.
 */
@Controller
@RequestMapping("/spittles")
public class SpittleControllerRest1 {

    private SpittleRepository spittleRepository;

    public SpittleControllerRest1(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }


    @RequestMapping(path = "/spittles", method = RequestMethod.GET)
    public List<Spittle> spittles1() {
        return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Spittle saveSpittle(@RequestBody Spittle spittle) {
        return spittleRepository.save(spittle);
    }
}
